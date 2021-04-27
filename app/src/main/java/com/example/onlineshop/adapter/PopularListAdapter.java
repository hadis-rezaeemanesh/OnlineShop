package com.example.onlineshop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineshop.R;
import com.example.onlineshop.model.Product;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PopularListAdapter extends RecyclerView.Adapter<PopularListAdapter.PopularListHolder> {

    private Context mContext;
    private List<Product> mProducts;

    public List<Product> getProducts() {
        return mProducts;
    }

    public void setProducts(List<Product> products) {
        mProducts = products;
    }

    @NonNull
    @Override
    public PopularListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull PopularListHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }


    public class PopularListHolder extends RecyclerView.ViewHolder {


        public PopularListHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
