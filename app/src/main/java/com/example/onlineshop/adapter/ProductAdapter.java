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

    public ProductAdapter(List<Product> products) {
        mProducts = products;
    }

    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_item, parent, false);
        return new ProductHolder(view);
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

        private ImageView mPhotoProduct;
        private TextView mNameProduct;
        private TextView mPriceProduct;
        public ProductHolder(@NonNull View itemView) {
            super(itemView);
            mPhotoProduct = itemView.findViewById(R.id.img_view_product);
            mNameProduct = itemView.findViewById(R.id.txt_view_name_product);
            mPriceProduct = itemView.findViewById(R.id.txt_view_price_product);
        }

        public void bind(Product product){
            mNameProduct.setText(product.getName());
            mPriceProduct.setText(product.getPrice());
        }
    }
}
