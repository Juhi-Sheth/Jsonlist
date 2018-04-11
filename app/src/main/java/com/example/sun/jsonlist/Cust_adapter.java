package com.example.sun.jsonlist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

/**
 * Created by Admin on 15-12-2017.
 */

public class Cust_adapter extends Adapter<Cust_adapter.MyViewHolder> {

    private static Cust_adapter.OnItemClickListener itemClickListner;
    private final Context context;
    private final List<Dataset> list;

    public Cust_adapter(Context context, List<Dataset> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.activity_rowlayout, parent, false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Dataset dt = list.get(position);


        holder.txtName.setText(dt.getName());
        holder.txtdescription.setText(dt.getDescription());
        holder.txtprice.setText(dt.getPrice());
        holder.txtchef.setText(dt.getChef());


        Glide.with(context).load(dt.getImage()).thumbnail(0.1f).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setItemClickListner(Cust_adapter.OnItemClickListener listner) {
        itemClickListner = listner;
    }

    public Dataset getDataset(int pos) {
        return list.get(pos);
    }

    public interface OnItemClickListener {
        public void OnClick(View view, int pos);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView txtName, txtdescription, txtprice, txtchef, cust_blog_continue;

        public MyViewHolder(View view) {
            super(view);

            imageView = view.findViewById(R.id.img);

            txtName = view.findViewById(R.id.txtName);
            txtdescription = view.findViewById(R.id.txtdescription);
            txtprice = view.findViewById(R.id.txtprice);
            txtchef = view.findViewById(R.id.txtchef);
            cust_blog_continue = view.findViewById(R.id.cust_blog_continue);
            cust_blog_continue.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (itemClickListner != null) {
                        itemClickListner.OnClick(view, MyViewHolder.this.getAdapterPosition());
                    }
                }
            });
        }
    }
}
