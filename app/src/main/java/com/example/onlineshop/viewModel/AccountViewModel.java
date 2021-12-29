package com.example.onlineshop.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.onlineshop.model.Customer;
import com.example.onlineshop.repository.CustomerRepository;
import com.example.onlineshop.utilities.QueryPreferences;

public class AccountViewModel extends AndroidViewModel {

    private CustomerRepository mRepository;
    private LiveData<Customer> mCustomerLiveData;
    private LiveData<Boolean> mRegisterLiveData;
    private LiveData<Customer> mSearchCustomerLiveData;

    private MutableLiveData<Boolean> mAlarmDialogLiveData = new MutableLiveData<>();
    private MutableLiveData<Boolean> mLocatorClickedLiveData = new MutableLiveData<>();
    private MutableLiveData<Boolean> mLoginClickedLiveData = new MutableLiveData<>();
    private MutableLiveData<Boolean> mLoginAccountLiveData = new MutableLiveData<>();

    private String mEmail;
    private String mLoginEmail;
    private String mUserName;

    public AccountViewModel(@NonNull Application application) {
        super(application);
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


    public void onTextChangedEmail(CharSequence charSequence, int i, int i1, int i2) {
        mEmail = charSequence.toString();
    }

    public void onTextChangedUserName(CharSequence charSequence, int i, int i1, int i2) {
        mUserName = charSequence.toString();
    }

    public void onTextChangedLoginEmail(CharSequence charSequence, int i, int i1, int i2) {
        mLoginEmail = charSequence.toString();
    }

    public void locationClicked(){
        mLocatorClickedLiveData.setValue(true);
    }

    public void alarmClicked(){
        mAlarmDialogLiveData.setValue(true);
    }

    public void loginClicked(){
        mLoginClickedLiveData.setValue(true);
    }

    public void signInClick() {
        Customer customer = new Customer(mEmail);
        if (mUserName != null)
            customer.setUserName(mUserName);
        QueryPreferences.setUserEmail(getApplication(), mEmail);
        mRepository.createCustomer(customer);
    }
}
