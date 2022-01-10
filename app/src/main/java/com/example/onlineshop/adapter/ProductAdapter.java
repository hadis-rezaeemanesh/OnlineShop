package com.example.onlineshop.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineshop.R;
import com.example.onlineshop.databinding.ProductItemBinding;
import com.example.onlineshop.model.Product;
import com.example.onlineshop.viewModel.ProductListViewModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductHolder> {

    private static final String TAG = "ProductAdapter";

    private Context mContext;
    private List<Product> mItems;
    private ProductListViewModel mProductViewModel;
    private int mListPosition;
    private LifecycleOwner mLifecycleOwner;


    public ProductAdapter(LifecycleOwner lifecycleOwner, ProductListViewModel productViewModel, List<Product> items, int listPosition) {
        mProductViewModel = productViewModel;
        mItems = items;
        mListPosition = listPosition;
        mLifecycleOwner = lifecycleOwner;
    }

    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ProductItemBinding binding = DataBindingUtil. inflate(
                LayoutInflater.from(mProductViewModel.getApplication()),
                R.layout.product_item,
                parent,
                false);
        return new ProductHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductHolder holder, int position) {

        holder.bind(position, mItems.get(position));
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

     class ProductHolder extends RecyclerView.ViewHolder {

        private ProductItemBinding mProductItemBinding;


        public ProductHolder(ProductItemBinding productItemBinding) {
            super(productItemBinding.getRoot());
            mProductItemBinding = productItemBinding;
            mProductItemBinding.setProductViewModel(mProductViewModel);
            mProductItemBinding.setLifecycleOwner(mLifecycleOwner);

            List<Product> items = mItems;
            /*List<String> productsName = new ArrayList<>();
            List<String> productsPrice = new ArrayList<>();*/
            List<Integer> mProductIds = new ArrayList<>();

            for (int i = 0; i < items.size(); i++) {
               /* productsName.add(items.get(i).getName());
                productsPrice.add(items.get(i).getPrice());*/
                mProductIds.add(items.get(i).getId());
            }

        }
        public void bind(int position, Product product){
            Log.d(TAG, "bind: " + product.getUrl());
//            mProductItemBinding.executePendingBindings();
            mProductItemBinding.setProduct(product);

            Picasso.get()
                    .load(product.getImages().get(0))
                    .placeholder(R.drawable.place_holder)
                    .into(mProductItemBinding.imgViewProduct);

        }
    }
}
