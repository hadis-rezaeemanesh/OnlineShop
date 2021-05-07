package com.example.onlineshop.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.onlineshop.R;
import com.example.onlineshop.adapter.ProductAdapter;
import com.example.onlineshop.databinding.FragmentProductListBinding;
import com.example.onlineshop.model.Product;
import com.example.onlineshop.viewModel.ProductViewModel;

import java.util.ArrayList;
import java.util.List;

public class ProductListFragment extends Fragment {

    public static final String ARGS_LIST_NAME = "listName";
    public static final String ARGS_LIST_STATE = "listState";

    private String nameList;
    private int stateList;

    private FragmentProductListBinding mBinding;
    private ProductViewModel mProductViewModel;
    private int page = 1;

    public ProductListFragment() {
        // Required empty public constructor
    }
    public static ProductListFragment newInstance(String nameList, int state) {
        ProductListFragment fragment = new ProductListFragment();
        Bundle args = new Bundle();
        args.putString(ARGS_LIST_NAME, nameList);
        args.putInt(ARGS_LIST_STATE, state);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        nameList = getArguments().getString(ARGS_LIST_NAME);
        stateList = getArguments().getInt(ARGS_LIST_STATE);

        mProductViewModel = new ViewModelProvider(this).get(ProductViewModel.class);
        mProductViewModel.fetchProductsAsync();
        mProductViewModel.getListProductLiveData().observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                setAdapters(products);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_product_list, container, false);

        initViews();
        listeners();
        return mBinding.getRoot();
    }

    private void setAdapters(List<Product> items) {
        ProductAdapter productAdapter = new ProductAdapter(getContext(), items);
        mBinding.recyclerViewProducts.setAdapter(productAdapter);
    }

    private void initViews() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                getActivity(),
                LinearLayoutManager.HORIZONTAL,
                false);
        linearLayoutManager.setReverseLayout(true);
        mBinding.recyclerViewProducts.setLayoutManager(linearLayoutManager);
    }

    private void listeners(){
        mBinding.recyclerViewProducts.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (page <= mProductViewModel.getPageCount().getValue() &&
                        mProductViewModel.getListProductLiveData().getValue().size() == 10)

                    mProductViewModel.fetchProductsAsync();
            }
        });
    }

}