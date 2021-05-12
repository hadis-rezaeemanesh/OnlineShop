package com.example.onlineshop.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.example.onlineshop.model.Product;
import com.example.onlineshop.repository.ProductRepository;

import java.util.List;

public class HomePageViewModel extends ProductViewModel {


    private ProductRepository mRepository;
    private final LiveData<Integer> mPerPage;
    private final LiveData<List<Product>> mVisitedProductsLiveData;
    private final LiveData<List<Product>> mNewestProductsLiveData;
    private final LiveData<List<Product>> mRatedProductsLiveData;

    public HomePageViewModel(@NonNull Application application) {
        super(application);
        mRepository = ProductRepository.getInstance();
        mPerPage = mRepository.getPerPage();
        mVisitedProductsLiveData = mRepository.getVisitedProductsLiveData();
        mNewestProductsLiveData = mRepository.getNewestProductsLiveData();
        mRatedProductsLiveData = mRepository.getRatedProductsLiveData();
    }

    public LiveData<Integer> getPerPage() {
        return mPerPage;
    }

    public LiveData<List<Product>> getVisitedProductsLiveData() {
        return mVisitedProductsLiveData;
    }

    public LiveData<List<Product>> getNewestProductsLiveData() {
        return mNewestProductsLiveData;
    }

    public LiveData<List<Product>> getRatedProductsLiveData() {
        return mRatedProductsLiveData;
    }

    public void fetchTotalProducts(){
        mRepository.fetchTotalProducts();
    }

    public void fetchNewestProducts(int perPage){
        mRepository.fetchNewestProductsList(perPage);
    }

    public void fetchVisitedProducts(int perPage){
        mRepository.fetchVisitedProductsList(perPage);
    }
    public void fetchRatedProducts(int perPage){
        mRepository.fetchRatedProductsList(perPage);
    }
}
