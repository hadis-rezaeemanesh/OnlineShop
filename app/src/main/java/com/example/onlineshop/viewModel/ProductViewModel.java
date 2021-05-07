package com.example.onlineshop.viewModel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.onlineshop.model.Product;
import com.example.onlineshop.repository.ProductRepository;

import java.util.List;

public class ProductViewModel extends ViewModel {
    public static final String TAG = "productViewModel";
    private ProductRepository mProductRepository;
    private final LiveData<List<Product>> mListProductLiveData;

    private MutableLiveData<Boolean> mOpenLiveData = new MutableLiveData<>();
    private LiveData<List<Product>> mNewestProductsLiveData;
    private LiveData<List<Product>> mRatedProductsLiveData;
    private LiveData<List<Product>> mVisitedProductsLiveData;


    public LiveData<List<Product>> getNewestProductsLiveData() {
        return mNewestProductsLiveData;
    }

    public LiveData<List<Product>> getRatedProductsLiveData() {
        return mRatedProductsLiveData;
    }

    public LiveData<List<Product>> getVisitedProductsLiveData() {
        return mVisitedProductsLiveData;
    }


    private LiveData<Integer> mPageCount;
    private LiveData<Integer> mCategoryItemId;


    public LiveData<List<Product>> getListProductLiveData() {
        return mListProductLiveData;
    }

    public LiveData<Integer> getPageCount() {
        return mPageCount;
    }

    public LiveData<Integer> getCategoryItemId() {
        return mCategoryItemId;
    }

    public MutableLiveData<Boolean> getOpenLiveData() {
        return mOpenLiveData;
    }

    public ProductViewModel() {
        mProductRepository = ProductRepository.getInstance();
        mListProductLiveData = mProductRepository.getListProductLiveData();
        mPageCount = mProductRepository.getPageCount();
        mCategoryItemId = mProductRepository.getCategoryItemId();
        mNewestProductsLiveData = mProductRepository.getNewestProductsLiveData();
        mRatedProductsLiveData = mProductRepository.getRatedProductsLiveData();
        mVisitedProductsLiveData = mProductRepository.getVisitedProductsLiveData();
    }

    public List<Product> getCurrentItems() {
        return mListProductLiveData.getValue();
    }

    public void fetchProductsAsync(){
        Log.d(TAG, "fetchProductsAsync: ");
        mProductRepository.fetchProductsAsync();
    }

    public void fetchNewestProducts(){
        mProductRepository.fetchNewestProductsList();
    }

    public void fetchRatedProducts(){
        mProductRepository.fetchRatedProductsList();
    }

    public void fetchVisitedProducts(){
        mProductRepository.fetchVisitedProductsList();
    }
}
