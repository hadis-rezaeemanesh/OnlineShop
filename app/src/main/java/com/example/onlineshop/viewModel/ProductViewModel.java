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

public class ProductViewModel extends AndroidViewModel {
    public static final String TAG = "productViewModel";
    private ProductRepository mProductRepository;
    private final LiveData<List<Product>> mListProductLiveData;
    private final LiveData<List<Product>> mNewestProductsLiveData;
    private final LiveData<List<Product>> mRatedProductsLiveData;
    private final LiveData<List<Product>> mVisitedProductsLiveData;

    private MutableLiveData<Boolean> mOpenLiveData = new MutableLiveData<>();

    private final LiveData<Integer> mPageCount;
    private final LiveData<Integer> mCategoryItemId;
    private final LiveData<Integer> mPerPage;

    public LiveData<Integer> getPerPage() {
        return mPerPage;
    }

    public LiveData<List<Product>> getNewestProductsLiveData() {
        return mNewestProductsLiveData;
    }

    public LiveData<List<Product>> getRatedProductsLiveData() {
        return mRatedProductsLiveData;
    }

    public LiveData<List<Product>> getVisitedProductsLiveData() {
        return mVisitedProductsLiveData;
    }


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

    public ProductViewModel(@NonNull Application application) {
        super(application);

        mProductRepository = ProductRepository.getInstance();
        mListProductLiveData = mProductRepository.getListProductLiveData();
        mPageCount = mProductRepository.getPageCount();
        mCategoryItemId = mProductRepository.getCategoryItemId();
        mNewestProductsLiveData = mProductRepository.getNewestProductsLiveData();
        mRatedProductsLiveData = mProductRepository.getRatedProductsLiveData();
        mVisitedProductsLiveData = mProductRepository.getVisitedProductsLiveData();
        mPerPage = mProductRepository.getPerPage();
    }

   /* public ProductViewModel() {
        mProductRepository = ProductRepository.getInstance();
        mListProductLiveData = mProductRepository.getListProductLiveData();
        mPageCount = mProductRepository.getPageCount();
        mCategoryItemId = mProductRepository.getCategoryItemId();
        mNewestProductsLiveData = mProductRepository.getNewestProductsLiveData();
        mRatedProductsLiveData = mProductRepository.getRatedProductsLiveData();
        mVisitedProductsLiveData = mProductRepository.getVisitedProductsLiveData();
    }*/

    public void openDrawer() {
        mOpenLiveData.setValue(true);
        Log.d(TAG, "open drawer from view model called");
    }

    public List<Product> getCurrentItems() {
        return mListProductLiveData.getValue();
    }

    public void fetchProductsAsync(){
        Log.d(TAG, "fetchProductsAsync: ");
        mProductRepository.fetchProductsAsync();
    }

    public void fetchTotalProducts(){
        mProductRepository.fetchTotalProducts();
    }

    public void fetchNewestProducts(int perPage){
        mProductRepository.fetchNewestProductsList(perPage);
    }

    public void fetchRatedProducts(int perPage){
        mProductRepository.fetchRatedProductsList(perPage);
    }

    public void fetchVisitedProducts(int perPage){
        mProductRepository.fetchVisitedProductsList(perPage);
    }
}
