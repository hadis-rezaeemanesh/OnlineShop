package com.example.onlineshop.adapter;

import android.content.Context;
import android.util.Log;
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

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryHolder> {
    private CategoryViewModel mCategoryViewModel;
    private Context mContext;
    private List<Category> mItems;
    private static final String TAG = "categoryAdapter";

    public CategoryAdapter(Context context, List<Category> items) {
        mContext = context;
        mItems = items;

    }

    @NonNull
    @Override
    public CategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CategoryItemBinding binding = DataBindingUtil.inflate( LayoutInflater.from(mContext),
                R.layout.category_item,
                parent,
                false);
        return new CategoryHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryHolder holder, int position) {
        holder.bind(mItems.get(position));

    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class CategoryHolder extends RecyclerView.ViewHolder {
        private CategoryItemBinding mItemBinding;
        public CategoryHolder(CategoryItemBinding itemBinding) {
            super(itemBinding.getRoot());
            mItemBinding = itemBinding;
        }

        public void bind(Category item){

            mItemBinding.txtViewCount.setText(item.getCount() + " کالا ");
            Log.d(TAG, "bind: " + item.getCount());

            mItemBinding.txtViewNameCategory.setText(item.getName());

            Picasso.get()
                    .load(item.getImage())
                    .placeholder(R.drawable.place_holder)
                    .into(mItemBinding.imgViewCategory);
        }
    }
}
