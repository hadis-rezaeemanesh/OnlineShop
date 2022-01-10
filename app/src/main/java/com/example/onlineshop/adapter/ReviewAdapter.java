package com.example.onlineshop.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineshop.R;
import com.example.onlineshop.databinding.ReviewItemBinding;
import com.example.onlineshop.model.Review;
import com.example.onlineshop.viewModel.ProductPageViewModel;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewHolder> {

    private ProductPageViewModel mViewModel;

    public ReviewAdapter(ProductPageViewModel viewModel) {
        mViewModel = viewModel;
    }

    @NonNull
    @Override
    public ReviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ReviewHolder(DataBindingUtil.inflate(
                LayoutInflater.from(mViewModel.getApplication()),
                R.layout.review_item,
                parent,
                false));
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewHolder holder, int position) {
        Review review = mViewModel.getReviewLiveData().getValue().get(position);
        holder.bind(review, position);
    }

    @Override
    public int getItemCount() {
        return mViewModel.getReviewLiveData().getValue().size();
    }

    public class ReviewHolder extends RecyclerView.ViewHolder {

        private final ReviewItemBinding mBinding;

        public ReviewHolder(ReviewItemBinding itemBinding) {
            super(itemBinding.getRoot());

            mBinding = itemBinding;
            mBinding.setProductPageViewModel(mViewModel);
        }

        public void bind(Review review, int position){
            mBinding.setPosition(position);
        }
    }
}
