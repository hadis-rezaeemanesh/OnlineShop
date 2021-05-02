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
    private LiveData<List<Product>> mListProductLiveData;

    private MutableLiveData<Boolean> mOpenLiveData = new MutableLiveData<>();

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

    public ProductViewModel(@NonNull Application application) {
        super(application);

        mProductRepository = ProductRepository.getInstance();
        mListProductLiveData = mProductRepository.getListProductLiveData();
        mPageCount = mProductRepository.getPageCount();
        mCategoryItemId = mProductRepository.getCategoryItemId();
    }

    public List<Product> getCurrentItems() {
        return mListProductLiveData.getValue();
    }

    public void fetchProductsAsync(int page){
        Log.d(TAG, "fetchProductsAsync: ");
        mProductRepository.fetchProductsAsync(page, mCategoryItemId.getValue() );
    }
}
