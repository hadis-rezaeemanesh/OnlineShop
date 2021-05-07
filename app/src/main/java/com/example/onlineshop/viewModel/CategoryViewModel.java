package com.example.onlineshop.viewModel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.onlineshop.model.Category;
import com.example.onlineshop.repository.ProductRepository;
import com.example.onlineshop.view.activity.MainActivity;
import com.example.onlineshop.view.fragment.ProductListFragment;

import java.util.List;

public class CategoryViewModel extends ViewModel {

    private static final String TAG = "categoryViewModel";
    private ProductRepository mProductRepository;
    private LiveData<List<Category>> mListCategoryLiveData;

    private LiveData<Integer> mPageCount;
    private LiveData<Integer> mCategoryItemId;

    public LiveData<Integer> getPageCount() {
        return mPageCount;
    }

    public LiveData<Integer> getCategoryItemId() {
        return mCategoryItemId;
    }

    public LiveData<List<Category>> getListCategoryLiveData() {
        return mListCategoryLiveData;
    }

    public CategoryViewModel() {
        mProductRepository = ProductRepository.getInstance();
        mListCategoryLiveData = mProductRepository.getListCategoryLiveData();
        mPageCount = mProductRepository.getPageCount();
        mCategoryItemId = mProductRepository.getCategoryItemId();
    }

    public List<Category> getCurrentCategories() {
        return mListCategoryLiveData.getValue();
    }


    public void fetchListCategory(){
        Log.d(TAG, "fetchListCategory: " );

        mProductRepository.fetchCategoriesAsync();

    }

    public void onClickListItem(int position) {
        Category item = mListCategoryLiveData.getValue().get(position);
        mProductRepository.fetchProductsAsync();
//        MainActivity.start(getApplication(), item.getName());
    }
}