package com.example.sun.jsonlist;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;


public class ShowActivity extends AppCompatActivity {

    ImageView img1;
    TextView txtName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        Dataset dataset = getIntent().getParcelableExtra("Data");

        img1=findViewById(R.id.img1);
        txtName=findViewById(R.id.txtNameCust);
        txtName.setText(dataset.getName());
       // img1.setIma(dataset.getImage());
        Glide.with(getApplication()).load(dataset.getImage()).diskCacheStrategy(DiskCacheStrategy.ALL).into(img1);

       //img1.setImageDrawable(Drawable.createFromPath(dataset.getImage()));

    }
}
