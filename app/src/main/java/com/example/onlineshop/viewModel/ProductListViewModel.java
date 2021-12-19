package com.example.onlineshop.viewModel;

import android.app.Application;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.onlineshop.model.Product;
import com.example.onlineshop.repository.ProductRepository;

import java.util.List;

public class ProductListViewModel extends ProductViewModel {
    public static final String TAG = "productViewModel";
    private ProductRepository mProductRepository;
    private final LiveData<List<Product>> mListProductLiveData;

    private MutableLiveData<Boolean> mOpenLiveData = new MutableLiveData<>();
    private MutableLiveData<Boolean> mSortDialogStart = new MutableLiveData<>();

    private final LiveData<Integer> mPageCount;
    private final LiveData<Integer> mCategoryItemId;


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

    public MutableLiveData<Boolean> getSortDialogStart() {
        return mSortDialogStart;
    }

    public ProductListViewModel(@NonNull Application application) {
        super(application);

        mProductRepository = ProductRepository.getInstance();
        mListProductLiveData = mProductRepository.getListProductLiveData();
        mPageCount = mProductRepository.getPageCount();
        mCategoryItemId = mProductRepository.getCategoryItemId();
    }

    public void openDrawer() {
        mOpenLiveData.setValue(true);
        Log.d(TAG, "open drawer from view model called");
    }

    public List<Product> getCurrentItems() {
        return mListProductLiveData.getValue();
    }

    public void fetchProductsAsync(int page){
        Log.d(TAG, "fetchProductsAsync: ");
        mProductRepository.fetchProductsAsync(page, mCategoryItemId.getValue());
    }

    public void sortClicked(){
        mSortDialogStart.setValue(true);
    }

}
