package com.example.onlineshop.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineshop.R;
import com.example.onlineshop.databinding.CartItemBinding;
import com.example.onlineshop.model.Product;
import com.example.onlineshop.viewModel.CartViewModel;
import com.squareup.picasso.Picasso;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartHolder> {

    private CartViewModel mViewModel;

    public CartAdapter(CartViewModel viewModel) {
        mViewModel = viewModel;
    }

    @NonNull
    @Override
    public CartHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder( CartAdapter.CartHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class CartHolder extends RecyclerView.ViewHolder {

        private CartItemBinding mBinding;

        public CartHolder(CartItemBinding cartItemBinding) {
            super(cartItemBinding.getRoot());
            mBinding = cartItemBinding;
            mBinding.executePendingBindings();

            mBinding.setCartViewModel(mViewModel);
        }

        public void bindItems(Product product, int position){
            mBinding.setPosition(position);
            Picasso.get()
                    .load(product.getImages().get(0))
                    .placeholder(R.drawable.place_holder)
                    .into(mBinding.cartImageProduct);
        }
    }
}
