package com.example.onlineshop.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.onlineshop.model.Customer;
import com.example.onlineshop.repository.CustomerRepository;

public class AccountViewModel extends ViewModel {

    private CustomerRepository mRepository;
    private LiveData<Customer> mCustomerLiveData;
    private LiveData<Boolean> mRegisterLiveData;
    private LiveData<Customer> mSearchCustomerLiveData;

    private MutableLiveData<Boolean> mAlarmDialogLiveData = new MutableLiveData<>();
    private MutableLiveData<Boolean> mLocatorClickedLiveData = new MutableLiveData<>();
    private MutableLiveData<Boolean> mLoginClickedLiveData = new MutableLiveData<>();
    private MutableLiveData<Boolean> mLoginAccountLiveData = new MutableLiveData<>();

    public AccountViewModel() {
        mRepository = CustomerRepository.getInstance();
        mCustomerLiveData = mRepository.getCustomerLiveData();
        mRegisterLiveData = mRepository.getRegisterLiveData();
        mSearchCustomerLiveData = mRepository.getSearchEmailLiveData();
    }

    public LiveData<Customer> getCustomerLiveData() {
        return mCustomerLiveData;
    }

    public LiveData<Boolean> getRegisterLiveData() {
        return mRegisterLiveData;
    }

    public LiveData<Customer> getSearchCustomerLiveData() {
        return mSearchCustomerLiveData;
    }

    public MutableLiveData<Boolean> getAlarmDialogLiveData() {
        return mAlarmDialogLiveData;
    }

    public MutableLiveData<Boolean> getLocatorClickedLiveData() {
        return mLocatorClickedLiveData;
    }

    public MutableLiveData<Boolean> getLoginClickedLiveData() {
        return mLoginClickedLiveData;
    }

    public MutableLiveData<Boolean> getLoginAccountLiveData() {
        return mLoginAccountLiveData;
    }

    public void locationClicked(){
        mLocatorClickedLiveData.setValue(true);
    }

    public void alarmClicked(){
        mAlarmDialogLiveData.setValue(true);
    }
}
