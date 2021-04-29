package com.example.onlineshop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineshop.R;
import com.example.onlineshop.databinding.ProductItemBinding;
import com.example.onlineshop.model.Product;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductHolder> {

    private Context mContext;
    private List<Product> mProducts;

    public List<Product> getProducts() {
        return mProducts;
    }

    public void setProducts(List<Product> products) {
        mProducts = products;
    }

    public ProductAdapter(Context context, List<Product> products) {
        mContext = context;
        mProducts = products;
    }

    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ProductItemBinding binding = DataBindingUtil. inflate(
                LayoutInflater.from(mContext),
                R.layout.product_item,
                parent,
                false);
        return new ProductHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductHolder holder, int position) {
        Product product = mProducts.get(position);
        holder.bind(product);
    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }

    public class ProductHolder extends RecyclerView.ViewHolder {

        private ProductItemBinding mProductItemBinding;


        public ProductHolder(ProductItemBinding productItemBinding) {
            super(productItemBinding.getRoot());

            mProductItemBinding = productItemBinding;
        }

        public void bind(Product product){
            Picasso.get()
                    .load(product.getUrl())
                    .placeholder(R.drawable.place_holder)
                    .into(mProductItemBinding.imgViewProduct);
        }
    }
}
