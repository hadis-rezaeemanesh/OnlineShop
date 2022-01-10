package com.example.onlineshop.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.onlineshop.R;
import com.example.onlineshop.adapter.ProductAdapter;
import com.example.onlineshop.adapter.ReviewAdapter;
import com.example.onlineshop.adapter.SliderViewPagerAdapter;
import com.example.onlineshop.databinding.FragmentProductDetailBinding;
import com.example.onlineshop.model.Product;
import com.example.onlineshop.model.Review;
import com.example.onlineshop.viewModel.ProductPageViewModel;
import com.example.onlineshop.viewModel.ProductListViewModel;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;

import java.util.ArrayList;
import java.util.List;

public class ProductPageFragment extends Fragment {

    private FragmentProductDetailBinding mBinding;
    private ProductPageViewModel mPageViewModel;
    private ProductListViewModel mProductViewModel;

    public ProductPageFragment() {
        // Required empty public constructor
    }

    public static ProductPageFragment newInstance() {
        ProductPageFragment fragment = new ProductPageFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mProductViewModel = new ViewModelProvider(this).get(ProductListViewModel.class);
        mPageViewModel = new ViewModelProvider(this).get(ProductPageViewModel.class);

        if (getArguments() != null){
            int id = getArguments().getInt("productId");
            Log.e("productItemClicked", "this id clicked in ppf " + id);
        }

        mPageViewModel.getProductLiveData().observe(this, new Observer<Product>() {
            @Override
            public void onChanged(Product product) {
                mBinding.setProductPageViewModel(mPageViewModel);
                mPageViewModel.fetchRelatedProducts(product.getRelatedIds());

                ArrayList<String> images = (ArrayList<String>) product.getImages();
                setupSliderAdapter(images);
            }
        });

        mPageViewModel.getRelatedProducts().observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                setAdapter(products);
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_product_detail, container, false);

        initViews();
        navListeners();

        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    private void navListeners(){
        mProductViewModel.getClickedItem().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                Bundle bundle = new Bundle();
                bundle.putInt("productId", integer);
                Navigation.findNavController(mBinding.getRoot()).navigate(R.id.productPageFragment, bundle);
            }
        });
    }

    private void setupSliderAdapter(ArrayList<String> images) {
        mBinding.productDetailSliderView.setSliderAdapter(new SliderViewPagerAdapter(getContext(), images));
        mBinding.productDetailSliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        mBinding.productDetailSliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        mBinding.productDetailSliderView.startAutoCycle();
    }

    private void setAdapter(List<Product> products){
        ProductAdapter adapter = new ProductAdapter(this, mProductViewModel,
                mPageViewModel.getRelatedProducts().getValue(), 0);
        mBinding.rvRelatedProducts.setAdapter(adapter);
    }

    private void initViews(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                getActivity(),
                LinearLayoutManager.HORIZONTAL,
                true);
        mBinding.rvRelatedProducts.setLayoutManager(linearLayoutManager);
    }


}