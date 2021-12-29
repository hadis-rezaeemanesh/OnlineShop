package com.example.onlineshop.view.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.onlineshop.R;
import com.example.onlineshop.adapter.CartAdapter;
import com.example.onlineshop.databinding.FragmentCartBinding;
import com.example.onlineshop.model.Product;
import com.example.onlineshop.viewModel.CartViewModel;

import java.util.List;

public class CartFragment extends VisibleFragment {

    private FragmentCartBinding mCartBinding;
    private CartViewModel mCartViewModel;
    public CartFragment() {
        // Required empty public constructor
    }

    public static CartFragment newInstance() {
        CartFragment fragment = new CartFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCartViewModel = new ViewModelProvider(this).get(CartViewModel.class);
        mCartViewModel.getCartLiveData().observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                setupAdapter();

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        mCartBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_cart, container, false);
        initViews();
        mCartBinding.setCartViewModel(mCartViewModel);
        return mCartBinding.getRoot();
    }

    private void initViews(){
        mCartBinding.recyclerViewCart.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void setupAdapter(){
        CartAdapter adapter = new CartAdapter(mCartViewModel);
        mCartBinding.recyclerViewCart.setAdapter(adapter);
    }
}