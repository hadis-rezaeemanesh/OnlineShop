package com.example.onlineshop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineshop.R;
import com.example.onlineshop.databinding.CategoryItemBinding;
import com.example.onlineshop.databinding.FragmentCategoryBinding;
import com.example.onlineshop.databinding.ProductItemBinding;
import com.example.onlineshop.model.Category;
import com.example.onlineshop.model.Product;
import com.example.onlineshop.viewModel.CategoryViewModel;
import com.squareup.picasso.Picasso;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryHolder> {
    private CategoryViewModel mCategoryViewModel;
    private Context mContext;

    public CategoryAdapter(CategoryViewModel categoryViewModel) {
        mCategoryViewModel = categoryViewModel;

    }

    @NonNull
    @Override
    public CategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CategoryHolder(DataBindingUtil.inflate(
                LayoutInflater.from(mCategoryViewModel.getApplication()),
                R.layout.category_item,
                parent,
                false));
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryHolder holder, int position) {
        Category item = mCategoryViewModel.getCurrentCategories().get(position);
        holder.bind(position, item);

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class CategoryHolder extends RecyclerView.ViewHolder {
        private CategoryItemBinding mItemBinding;
        public CategoryHolder(CategoryItemBinding itemBinding) {
            super(itemBinding.getRoot());
            mItemBinding = itemBinding;
            itemBinding.setCategoryViewModel(mCategoryViewModel);
        }

        public void bind(int position, Category item){
            mItemBinding.setPosition(position);

            Picasso.get()
                    .load(item.getImage())
                    .placeholder(R.drawable.place_holder)
                    .into(mItemBinding.imgViewCategory);
        }
    }
}
