package com.example.onlineshop.view.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.onlineshop.R;
import com.example.onlineshop.adapter.CartAdapter;
import com.example.onlineshop.databinding.FragmentCartBinding;
import com.example.onlineshop.model.Product;
import com.example.onlineshop.observers.ObserverEvent;
import com.example.onlineshop.utilities.QueryPreferences;
import com.example.onlineshop.viewModel.CartViewModel;

import java.util.List;

public class CartFragment extends VisibleFragment {

    private FragmentCartBinding mCartBinding;
    private CartViewModel mCartViewModel;
    private CartAdapter mAdapter;

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
        mCartViewModel.fetchCartItems();
        mCartViewModel.getCartProductItem().observe(this, new Observer<List<Product>>() {
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

        mCartViewModel.getStartAccountDialog().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
                dialogBuilder.setMessage("برای ثبت نام ابتدا باید وارد حساب کاربری خود شوید.");
                dialogBuilder.setCancelable(true);
                dialogBuilder.setPositiveButton("ورود به حساب کاربری", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Navigation.findNavController(mCartBinding.getRoot()).navigate(R.id.accountFragment);
                        dialogInterface.dismiss();
                    }
                });
                dialogBuilder.setNegativeButton("انصراف", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                dialogBuilder.show();
            }
        });

        LiveData<Boolean> sendLiveData = mCartViewModel.getSendDialogLiveData();
        sendLiveData.observe(getViewLifecycleOwner(), new ObserverEvent<Boolean>(this, sendLiveData) {
            @Override
            public void onChanged(Boolean aBoolean) {
                super.onChanged(aBoolean);
                if (aBoolean){
                    Navigation.findNavController(mCartBinding.getRoot()).navigate(R.id.orderFragment);
                }
            }
        });

        return mCartBinding.getRoot();
    }

    private void initViews(){
        mCartBinding.recyclerViewCart.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void setupAdapter(){
        mAdapter = new CartAdapter(mCartViewModel);
        mCartBinding.recyclerViewCart.setAdapter(mAdapter);
    }

}