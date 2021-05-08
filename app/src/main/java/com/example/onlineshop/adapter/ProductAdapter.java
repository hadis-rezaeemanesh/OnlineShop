package com.example.onlineshop.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineshop.R;
import com.example.onlineshop.databinding.ActivityOnlineShopBinding;
import com.example.onlineshop.databinding.ProductItemBinding;
import com.example.onlineshop.model.Product;
import com.example.onlineshop.viewModel.ProductViewModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductHolder> {

    private static final String TAG = "ProductAdapter";

    private Context mContext;
    private List<Product> mItems;
    private ProductViewModel mProductViewModel;


    public ProductAdapter(ProductViewModel productViewModel, List<Product> items) {
        mProductViewModel = productViewModel;
        mItems = items;
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

            List<Product> items = mItems;
            List<String> productsName = new ArrayList<>();
            List<String> productsPrice = new ArrayList<>();

            for (int i = 0; i < items.size(); i++) {
                productsName.add(items.get(i).getName());
                productsPrice.add(items.get(i).getPrice());
            }

            mProductItemBinding.setProductNameList(productsName);
            mProductItemBinding.setProductPriceList(productsPrice);
        }

        public void bind(int position, Product product){
            Log.d(TAG, "bind: " + product.getName());
            mProductItemBinding.setPosition(position);

            Picasso.get()
                    .load(product.getUrl())
                    .placeholder(R.drawable.place_holder)
                    .into(mProductItemBinding.imgViewProduct);
        }
    }
}
