package com.example.onlineshop.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineshop.R;
import com.example.onlineshop.databinding.CategoryItemBinding;
import com.example.onlineshop.model.Category;
import com.example.onlineshop.viewModel.CategoryViewModel;
import com.squareup.picasso.Picasso;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryHolder> {
    private CategoryViewModel mCategoryViewModel;
    private static final String TAG = "categoryAdapter";

    public CategoryAdapter(CategoryViewModel viewModel) {
        mCategoryViewModel = viewModel;

    }

    @NonNull
    @Override
    public CategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CategoryItemBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(mCategoryViewModel.getApplication()),
                R.layout.category_item,
                parent,
                false);
        return new CategoryHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryHolder holder, int position) {
        Category item = mCategoryViewModel.getCurrentCategories().get(position);
        holder.bind(position, item);

    }

    @Override
    public int getItemCount() {
        return mCategoryViewModel.getCurrentCategories().size();
    }

    class CategoryHolder extends RecyclerView.ViewHolder {
        private CategoryItemBinding mItemBinding;
        public CategoryHolder(CategoryItemBinding itemBinding) {
            super(itemBinding.getRoot());
            mItemBinding = itemBinding;
            mItemBinding.setCategoryViewModel(mCategoryViewModel);
        }

        public void bind(int position, Category item){

            mItemBinding.setPosition(position);
            mItemBinding.executePendingBindings();

            Picasso.get()
                    .load(item.getImage())
                    .placeholder(R.drawable.place_holder)
                    .into(mItemBinding.imgViewCategory);
        }
    }
}
