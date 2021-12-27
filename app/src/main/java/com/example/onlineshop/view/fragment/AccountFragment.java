package com.example.onlineshop.view.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.onlineshop.ObserverEvent;
import com.example.onlineshop.R;
import com.example.onlineshop.databinding.FragmentAccountBinding;
import com.example.onlineshop.viewModel.AccountViewModel;

public class AccountFragment extends Fragment {

    private FragmentAccountBinding mBinding;
    private AccountViewModel mViewModel;


    public AccountFragment() {
        // Required empty public constructor
    }

    public static AccountFragment newInstance() {
        AccountFragment fragment = new AccountFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(AccountViewModel.class);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_account,
                container,
                false);
        mBinding.setAccountViewModel(mViewModel);
        listeners();
        return mBinding.getRoot();
    }

    private void listeners(){
        LiveData<Boolean> locatorLiveData = mViewModel.getLocatorClickedLiveData();
        locatorLiveData.observe(
                getViewLifecycleOwner(),
                new ObserverEvent<Boolean>(this, locatorLiveData) {
            @Override
            public void onChanged(Boolean aBoolean) {
                super.onChanged(aBoolean);
                if (aBoolean) {
                    Navigation.findNavController(mBinding.getRoot()).navigate(R.id.locatorFragment);
                }
            }
        });
    }
}