package com.example.onlineshop.view.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.onlineshop.R;
import com.example.onlineshop.adapter.ProductAdapter;
import com.example.onlineshop.databinding.ActivityOnlineShopBinding;
import com.example.onlineshop.model.Product;
import com.example.onlineshop.viewModel.HomePageViewModel;
import com.example.onlineshop.viewModel.ProductListViewModel;

import java.util.List;

public class HomePageFragment extends Fragment {

    private ActivityOnlineShopBinding mBinding;
    private HomePageViewModel mViewModel;
    private ProductListViewModel mProductsViewModel;
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

           mViewModel = new ViewModelProvider(this).get(HomePageViewModel.class);
           mProductsViewModel = new ViewModelProvider(this).get(ProductListViewModel.class);
           mViewModel.fetchTotalProducts();
           mViewModel.getPerPage().observe(this, new Observer<Integer>() {
               @Override
               public void onChanged(Integer integer) {
                   mViewModel.fetchNewestProducts(integer);
                   mViewModel.fetchRatedProducts(integer);
                   mViewModel.fetchVisitedProducts(integer);
               }
           });

        mViewModel.getNewestProductsLiveData().observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                setupAdapter(mViewModel.getNewestProductsLiveData(),
                        mBinding.recyclerProductsNewest,
                        1);
            }
        });
        mViewModel.getRatedProductsLiveData().observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                setupAdapter(mViewModel.getRatedProductsLiveData(),
                        mBinding.recyclerProductsRated,
                        2);
            }
        });
        mViewModel.getVisitedProductsLiveData().observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                setupAdapter(mViewModel.getVisitedProductsLiveData(),
                        mBinding.recyclerProductsVisited,
                        3);
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

    private void setupAdapter(
            LiveData<List<Product>> listLiveData, RecyclerView recyclerView, int listPosition) {
        ProductAdapter adapter = new ProductAdapter(
                this ,mProductsViewModel, listLiveData.getValue(), listPosition);
        recyclerView.setAdapter(adapter);
        /*mBinding.recyclerProductsNewest.setAdapter(adapter);
        mBinding.recyclerProductsRated.setAdapter(adapter);
        mBinding.recyclerProductsVisited.setAdapter(adapter);*/


    }

}