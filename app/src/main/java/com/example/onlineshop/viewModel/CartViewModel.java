package com.example.onlineshop.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.onlineshop.model.Product;
import com.example.onlineshop.repository.ProductRepository;
import com.example.onlineshop.utilities.QueryPreferences;

import java.util.List;

public class CartViewModel extends AndroidViewModel {

    private ProductRepository mRepository;
    private LiveData<List<Product>> mCartLiveData;

    public LiveData<List<Product>> getCartLiveData() {
        return mCartLiveData;
    }

    public CartViewModel(Application application) {
        super(application);
        mRepository = ProductRepository.getInstance();
        mCartLiveData = mRepository.getCartLiveData();
    }

    public void deleteFromCart(Product item){
        QueryPreferences.removeCartProduct(getApplication(), item);
    }

    public void addCartAgain(Product item){
        QueryPreferences.addCartProduct(getApplication(), item);
    }

    public String setTotalPrice() {
        int total = 0;
        List<Product> items = QueryPreferences.getCartProducts(getApplication());
        for (int i = 0; i < items.size(); i++) {
            total += Integer.parseInt(items.get(i).getPrice());
        }
        return total + " تومان ";
    }
}
