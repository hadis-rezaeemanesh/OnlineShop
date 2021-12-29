package com.example.onlineshop.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.onlineshop.R;
import com.example.onlineshop.adapter.ProductAdapter;
import com.example.onlineshop.adapter.SliderViewPagerAdapter;
import com.example.onlineshop.databinding.ActivityOnlineShopBinding;
import com.example.onlineshop.databinding.FragmentOnlineShopBinding;
import com.example.onlineshop.model.Product;
import com.example.onlineshop.viewModel.HomePageViewModel;
import com.example.onlineshop.viewModel.ProductListViewModel;
import com.google.android.material.navigation.NavigationView;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;

import java.util.ArrayList;
import java.util.List;

public class HomePageFragment extends VisibleFragment {

    private FragmentOnlineShopBinding mBinding;
    private HomePageViewModel mViewModel;
    private ProductListViewModel mProductsViewModel;
    public static final String TAG = "homePageFragment";

    public HomePageFragment() {
        // Required empty public constructor
    }
    public static HomePageFragment newInstance() {
        Log.d(TAG, "newInstance: HomePageFragment");

        HomePageFragment fragment = new HomePageFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG, "onCreate: HomePageFragment");


           mViewModel = new ViewModelProvider(this).get(HomePageViewModel.class);
           mProductsViewModel = new ViewModelProvider(this).get(ProductListViewModel.class);
           mViewModel.fetchTotalProducts();
           mViewModel.fetchOfferPhotos();
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
        mViewModel.getOfferPhotosLiveData().observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> strings) {
                setupSliderAdapter((ArrayList<String>) strings);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_online_shop,
                container, false);

        initViews();
        navListener();

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

    }

    private void navListener() {

        mProductsViewModel.getClickedItem().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer id) {
//                Log.e("productItemClicked", "this id clicked in pvm change LiveData");
                Bundle bundle = new Bundle();
                bundle.putInt("productId", id);
                Navigation.findNavController(mBinding.getRoot()).
                        navigate(R.id.productPageFragment, bundle);
            }
        });
    }

    private void setupSliderAdapter(ArrayList<String> itemImages) {
        mBinding.imageSlider.setSliderAdapter(new SliderViewPagerAdapter(getContext(), itemImages));
        mBinding.imageSlider.setIndicatorAnimation(IndicatorAnimationType.WORM);
        mBinding.imageSlider.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        mBinding.imageSlider.startAutoCycle();
    }


}