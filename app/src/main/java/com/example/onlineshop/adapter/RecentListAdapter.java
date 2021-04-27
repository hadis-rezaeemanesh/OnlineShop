package com.example.onlineshop.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecentListAdapter extends RecyclerView.Adapter<RecentListAdapter.RecentListHolder> {
    @NonNull
    @Override
    public RecentListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecentListHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class RecentListHolder extends RecyclerView.ViewHolder {
        public RecentListHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
