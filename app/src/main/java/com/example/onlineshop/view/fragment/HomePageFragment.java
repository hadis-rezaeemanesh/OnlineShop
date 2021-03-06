package com.example.onlineshop.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.onlineshop.R;
import com.example.onlineshop.adapter.CategoryAdapter;
import com.example.onlineshop.adapter.ProductAdapter;
import com.example.onlineshop.databinding.ActivityOnlineShopBinding;
import com.example.onlineshop.model.Category;
import com.example.onlineshop.model.Product;
import com.example.onlineshop.viewModel.CategoryViewModel;
import com.example.onlineshop.viewModel.ProductViewModel;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

public class HomePageFragment extends Fragment {

    private ActivityOnlineShopBinding mBinding;
    private ProductViewModel mViewModel;
    public static final String TAG = "homePageFragment";

    public HomePageFragment() {
        // Required empty public constructor
    }
    public static HomePageFragment newInstance() {
        HomePageFragment fragment = new HomePageFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       /*    mViewModel = new ViewModelProvider(this).get(CategoryViewModel.class);
        mViewModel.fetchListCategory();
        mViewModel.getListCategoryLiveData().observe(this, new Observer<List<Category>>() {
            @Override
            public void onChanged(List<Category> categories) {
                setupAdapter(categories);

            }
        });*/

        mViewModel = new ViewModelProvider(this).get(ProductViewModel.class);
        mViewModel.fetchNewestProducts();
        mViewModel.getNewestProductsLiveData().observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                setupAdapter(products);
            }
        });
        mViewModel.fetchRatedProducts();
        mViewModel.getRatedProductsLiveData().observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                setupAdapter(products);
            }
        });
        mViewModel.fetchVisitedProducts();
        mViewModel.getVisitedProductsLiveData().observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                setupAdapter(products);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(inflater, R.layout.activity_online_shop, container, false);

        initViews();



        return mBinding.getRoot();
    }

    private void initViews() {
        mBinding.recyclerProductsNewest.setLayoutManager(new LinearLayoutManager(
                getContext(),
                LinearLayoutManager.HORIZONTAL,
                false)
        );

        mBinding.recyclerProductsVisited.setLayoutManager(new LinearLayoutManager(
                getContext(),
                LinearLayoutManager.HORIZONTAL,
                false
        ));

        mBinding.recyclerProductsRated.setLayoutManager(new LinearLayoutManager(
                getContext(),
                LinearLayoutManager.HORIZONTAL,
                false
        ));
    }

    private void setupAdapter(List<Product> items) {
        ProductAdapter adapter = new ProductAdapter(getContext(),items);
        mBinding.recyclerProductsNewest.setAdapter(adapter);
        mBinding.recyclerProductsRated.setAdapter(adapter);
        mBinding.recyclerProductsVisited.setAdapter(adapter);


    }

}