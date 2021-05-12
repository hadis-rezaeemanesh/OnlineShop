package com.example.onlineshop.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModel;

import com.example.onlineshop.R;
import com.example.onlineshop.databinding.LayoutSliderImageItemBinding;
import com.smarteist.autoimageslider.SliderViewAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SliderViewPagerAdapter extends
        SliderViewAdapter<SliderViewPagerAdapter.SliderViewPagerHolder> {

    public static final String TAG = "SliderViewPagerAdapter";
    private Context mContext;
    private List<String> mImages = new ArrayList<>();

    public SliderViewPagerAdapter(Context context, List<String> images) {
        mContext = context;
        mImages.addAll(images);

    }

    @Override
    public SliderViewPagerHolder onCreateViewHolder(ViewGroup parent) {
        return new SliderViewPagerHolder(DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.layout_slider_image_item,
                parent,
                false));

    }

    @Override
    public void onBindViewHolder(SliderViewPagerHolder holder, int i) {
        String imageUrl = mImages.get(i);
        holder.bindProductImage(imageUrl);

    }

    @Override
    public int getCount() {
        return mImages.size();
    }

    public class SliderViewPagerHolder extends SliderViewAdapter.ViewHolder {

        private LayoutSliderImageItemBinding mBinding;

        public SliderViewPagerHolder(LayoutSliderImageItemBinding itemBinding) {
            super(itemBinding.getRoot());
            mBinding = itemBinding;
        }

        public void bindProductImage(String imageUrl) {
            Picasso.get()
                    .load(imageUrl)
                    .placeholder(R.drawable.place_holder)
                    .into(mBinding.imgViewSlider);
        }
    }
}
