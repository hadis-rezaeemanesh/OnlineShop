package com.example.onlineshop.viewModel;

import android.app.Application;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.onlineshop.model.Category;
import com.example.onlineshop.repository.ProductRepository;
import java.util.List;

public class CategoryViewModel extends AndroidViewModel {

    private static final String TAG = "categoryViewModel";
    private ProductRepository mProductRepository;

    private final LiveData<List<Category>> mListCategoryLiveData;
    private final LiveData<Integer> mPageCount;
    private MutableLiveData<String> mNavigateLiveData = new MutableLiveData<>();

    public LiveData<Integer> getPageCount() {
        return mPageCount;
    }

    public LiveData<List<Category>> getListCategoryLiveData() {
        return mListCategoryLiveData;
    }

    public MutableLiveData<String> getNavigateLiveData() {
        return mNavigateLiveData;
    }

    public CategoryViewModel(@NonNull Application application) {
        super(application);

        mProductRepository = ProductRepository.getInstance();
        mListCategoryLiveData = mProductRepository.getListCategoryLiveData();
        mPageCount = mProductRepository.getPageCount();
    }

    public List<Category> getCurrentCategories() {
        return mListCategoryLiveData.getValue();
    }


    public void fetchListCategory(int page){
        Log.d(TAG, "fetchListCategory: " );

        mProductRepository.fetchCategoriesAsync(page);

    }

    public void onClickListItem(int position) {
        Category item = mListCategoryLiveData.getValue().get(position);
        mProductRepository.fetchProductsAsync(1, item.getId());
        mNavigateLiveData.setValue(item.getName());
    }
}