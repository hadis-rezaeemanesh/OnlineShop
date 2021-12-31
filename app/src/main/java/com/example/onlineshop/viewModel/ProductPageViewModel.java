package com.example.onlineshop.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.example.onlineshop.model.Product;
import com.example.onlineshop.repository.ProductRepository;
import com.example.onlineshop.utilities.QueryPreferences;

import java.util.List;

public class ProductPageViewModel extends ProductViewModel {

    private LiveData<Product> mProductLiveData;
    private LiveData<List<Product>> mRelatedProducts;
    private ProductRepository mRepository;

    public LiveData<Product> getProductLiveData() {
        return mProductLiveData;
    }

    public LiveData<List<Product>> getRelatedProducts() {
        return mRelatedProducts;
    }

    public ProductPageViewModel(@NonNull Application application) {
        super(application);
        mRepository = ProductRepository.getInstance();
        mProductLiveData = mRepository.getProductItemLiveData();
        mRelatedProducts = mRepository.getRatedProductsLiveData();
    }

    public void fetchRelatedProducts(List<Integer> productRelatedIds){
        mRepository.fetchRelatedProducts(productRelatedIds);
    }

    public void addToCartClicked(){
        Product item = mProductLiveData.getValue();
        item.setCountInCart(1);
        QueryPreferences.addCartProduct(getApplication(), item);
        mRepository.setCartItemLiveData(QueryPreferences.getCartProducts(getApplication()));
    }
}
