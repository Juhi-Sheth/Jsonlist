package com.example.sun.jsonlist;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.sun.jsonlist.util.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    static final String url = "https://api.androidhive.info/json/shimmer/menu.php";
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;
    Cust_adapter adapter;
    ProgressDialog dialog;

    List<Dataset> list = new ArrayList<Dataset>();

    String str;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swipeRefreshLayout = findViewById(R.id.simpleSwipeRefreshLayout);
        recyclerView = findViewById(R.id.items);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading");
        dialog.show();

        adapter = new Cust_adapter(this, list);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        getDetails();

        adapter.setItemClickListner(new Cust_adapter.OnItemClickListener() {
            @Override
            public void OnClick(View view, int pos) {

                Toast.makeText(getApplicationContext(),"Number"+pos,Toast.LENGTH_SHORT).show();

               Dataset dataset=adapter.getDataset(pos);
//                Bundle bundle=new Bundle();
//                bundle.putParcelable("Data",dataset);
                Intent intent=new Intent(getApplication(),ShowActivity.class);
                intent.putExtra("Data",dataset);
                startActivity(intent);



            }
        });


      /*  swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // cancel the Visual indication of a refresh
                swipeRefreshLayout.setRefreshing(true);
                getDetails();
            }
        });*/


    }

    private void getDetails() {
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("MainActivity", response);

                setData(response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("", "onErrorResponse: " + error.getLocalizedMessage());
                Toast.makeText(getApplicationContext(), "Something Went Wrong", Toast.LENGTH_SHORT).show();

            }
        });
        AppController.getInstance().addToRequestQueues(request, "MainActivty");
    }

    private void setData(String response) {
        try {
            dialog.dismiss();
            if (swipeRefreshLayout.isRefreshing())
                Toast.makeText(this, "Refresh Complete", Toast.LENGTH_SHORT).show();
            swipeRefreshLayout.setRefreshing(false);

            JSONArray jsonArray = new JSONArray(response);

            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject jsonObject2 = jsonArray.getJSONObject(i);

                Dataset dataSet = new Dataset();

                dataSet.setImage(jsonObject2.getString("thumbnail"));

                dataSet.setName(jsonObject2.getString("name"));
                dataSet.setDescription(jsonObject2.getString("description"));
                dataSet.setPrice(jsonObject2.getString("price"));
                dataSet.setChef(jsonObject2.getString("chef"));


                list.add(dataSet);
            }
            adapter.notifyDataSetChanged();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private void shuffleItems() {

        Collections.shuffle(list, new Random(System.currentTimeMillis()));

        recyclerView.setAdapter(adapter); // set the Adapter to RecyclerView
    }
}
