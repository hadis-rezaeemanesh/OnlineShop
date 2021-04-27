package com.example.onlineshop.view.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.onlineshop.R;
import com.example.onlineshop.adapter.ProductAdapter;
import com.example.onlineshop.databinding.FragmentProductListBinding;
import com.example.onlineshop.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductListFragment extends Fragment {

    private FragmentProductListBinding mBinding;

    public ProductListFragment() {
        // Required empty public constructor
    }
    public static ProductListFragment newInstance() {
        ProductListFragment fragment = new ProductListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_product_list, container, false);

        initViews();
        setAdapters();
        return mBinding.getRoot();
    }

    private void setAdapters() {
        List<Product> items = new ArrayList<>();
        Product product = new Product();
        product.setName("Phone");
        product.setPrice("5000000 toman");
        for (int i = 0; i <100 ; i++) {
            items.add(product);
        }
        ProductAdapter productAdapter = new ProductAdapter(items);
        mBinding.recyclerViewProducts.setAdapter(productAdapter);
    }

    private void initViews() {
        mBinding.recyclerViewProducts.setLayoutManager(new LinearLayoutManager(
                getActivity(),
                LinearLayoutManager.HORIZONTAL,
                false));
    }

}