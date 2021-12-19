package com.example.onlineshop.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.onlineshop.model.Product;
import com.example.onlineshop.repository.ProductRepository;

import java.util.List;

public class SortListViewModel extends ViewModel {

    private ProductRepository mRepository;
    private LiveData<Integer> mPerPage;
    private LiveData<Integer> mCategoryId;
    private LiveData<List<Product>> mCheapestProductsLiveData;
    private MutableLiveData<Boolean> mDismissDialog = new MutableLiveData<>();


    public SortListViewModel() {
        mRepository = ProductRepository.getInstance();
        mCategoryId = mRepository.getCategoryItemId();
        mPerPage = mRepository.getPerPage();
        mCheapestProductsLiveData = mRepository.getListProductLiveData();
    }

    public LiveData<Integer> getPerPage() {
        return mPerPage;
    }

    public LiveData<Integer> getCategoryId() {
        return mCategoryId;
    }

    public LiveData<List<Product>> getCheapestProductsLiveData() {
        return mCheapestProductsLiveData;
    }

    public MutableLiveData<Boolean> getDismissDialog() {
        return mDismissDialog;
    }

    public void fetchTotalProductsForCategory(){
        mRepository.fetchTotalProductsForCategory(mCategoryId.getValue());
    }
}
