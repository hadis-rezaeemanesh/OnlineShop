package com.example.onlineshop.viewModel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.onlineshop.repository.CustomerRepository;
import com.example.onlineshop.repository.ProductRepository;

public class ProductViewModel extends AndroidViewModel {

    private ProductRepository mRepository;
    private CustomerRepository mCustomerRepository;
    private MutableLiveData<Integer> mClickedItem;

    public MutableLiveData<Integer> getClickedItem() {
        mClickedItem = new MutableLiveData<>();
        return mClickedItem;
    }

    public ProductViewModel(@NonNull Application application) {
        super(application);

        mRepository = ProductRepository.getInstance();
        mCustomerRepository = CustomerRepository.getInstance();
    }

    public void onItemClicked(int id){

        mRepository.fetchProductWithId(id);
        mCustomerRepository.fetchProductReviews(id);
        mClickedItem.setValue(id);
    }

}
