package com.example.onlineshop.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.onlineshop.model.Product;
import com.example.onlineshop.repository.ProductRepository;

import java.util.List;

public class ProductViewModel extends ViewModel {
    private ProductRepository mProductRepository;
    private LiveData<List<Product>> mListProductLiveData;

    public LiveData<List<Product>> getListProductLiveData() {
        return mListProductLiveData;
    }

    public ProductViewModel() {
        mProductRepository = new ProductRepository();
        mListProductLiveData = mProductRepository.getListProductLiveData();

    }
}
