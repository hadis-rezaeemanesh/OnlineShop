package com.example.onlineshop.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NewestListAdapter extends RecyclerView.Adapter<NewestListAdapter.NewestListHolder> {

    @NonNull
    @Override
    public NewestListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull NewestListHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class NewestListHolder extends RecyclerView.ViewHolder{

        public NewestListHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
