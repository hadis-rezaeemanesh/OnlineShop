package com.example.onlineshop.view.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.onlineshop.observers.ObserverEvent;
import com.example.onlineshop.R;
import com.example.onlineshop.databinding.FragmentAccountBinding;
import com.example.onlineshop.model.Customer;
import com.example.onlineshop.utilities.QueryPreferences;
import com.example.onlineshop.viewModel.AccountViewModel;

public class AccountFragment extends VisibleFragment {

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

        LiveData<Boolean> alarmLiveData = mViewModel.getAlarmDialogLiveData();
        alarmLiveData.observe(
                getViewLifecycleOwner(),
                new ObserverEvent<Boolean>(this, alarmLiveData) {
                    @Override
                    public void onChanged(Boolean aBoolean) {
                        super.onChanged(aBoolean);
                        if (aBoolean)
                            Navigation.findNavController(mBinding.getRoot()).navigate(R.id.alarmFragment);
                    }
                });

        LiveData<Boolean> loginLiveData = mViewModel.getLoginClickedLiveData();
        loginLiveData.observe(
                getViewLifecycleOwner(),
                new ObserverEvent<Boolean>(this, loginLiveData) {
                    @Override
                    public void onChanged(Boolean aBoolean) {
                        super.onChanged(aBoolean);
                        if (aBoolean)
                            Navigation.findNavController(mBinding.getRoot()).navigate(R.id.loginFragment);
                    }
                }
        );

        LiveData<Boolean> registerLiveData = mViewModel.getRegisterLiveData();
        registerLiveData.observe(
                getViewLifecycleOwner(),
                new ObserverEvent<Boolean>(this, registerLiveData) {
                    @Override
                    public void onChanged(Boolean aBoolean) {
                        super.onChanged(aBoolean);
                        if (aBoolean)
                            Toast.makeText(
                                    getActivity(),
                                    "ثبت نام شما انجام شد.",
                                    Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(
                                    getActivity(),
                                    " قبلا ثبت نام کرده اید یا ایمیل شما نامعتبر است.",
                                    Toast.LENGTH_LONG).show();
                    }
                }
        );

        mViewModel.getSearchCustomerLiveData().observe(getViewLifecycleOwner(), new Observer<Customer>() {
            @Override
            public void onChanged(Customer customer) {
                if (customer == null) {
                    Toast.makeText(
                            getActivity(),
                            "ایمیل شما نامعتبر است.",
                            Toast.LENGTH_LONG).show();
                } else {
                    QueryPreferences.setUserEmail(getActivity(), customer.getEmail());
                    QueryPreferences.setUserName(getActivity(), customer.getUserName());

                    mBinding.editTxtEmail.setText(QueryPreferences.getUserEmail(getActivity()));
                    mBinding.editTxtUserName.setText(QueryPreferences.getUserName(getActivity()));
                }
            }
        });
    }
}