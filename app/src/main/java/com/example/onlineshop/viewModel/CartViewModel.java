package com.example.onlineshop.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.onlineshop.model.Product;
import com.example.onlineshop.repository.ProductRepository;
import com.example.onlineshop.utilities.QueryPreferences;

import java.util.List;

public class CartViewModel extends AndroidViewModel {

    private ProductRepository mRepository;
    private MutableLiveData<List<Product>> mCartProductItem;
    private MutableLiveData<Boolean> mStartAccountDialog = new MutableLiveData<>();
    private MutableLiveData<Boolean> mSendDialogLiveData = new MutableLiveData<>();



    public CartViewModel(Application application) {
        super(application);
        mRepository = ProductRepository.getInstance();
        mCartProductItem = mRepository.getCartLiveData();

    }

    public MutableLiveData<List<Product>> getCartProductItem() {
        return mCartProductItem;
    }

    public MutableLiveData<Boolean> getStartAccountDialog() {
        return mStartAccountDialog;
    }

    public MutableLiveData<Boolean> getSendDialogLiveData() {
        return mSendDialogLiveData;
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
        if(items != null) {
            for (int i = 0; i < items.size(); i++) {
                total += Integer.parseInt(items.get(i).getPrice());
            }
            return total + " تومان ";
        }else return total + " ";
    }

    public void fetchCartItems() {
        List<Product> cartItems = QueryPreferences.getCartProducts(getApplication());
        if (cartItems != null)
            mCartProductItem.setValue(cartItems);
    }

    public void requestOrder() {
        if (QueryPreferences.getUserEmail(getApplication()) == null)
            mStartAccountDialog.setValue(true);
        else
            mSendDialogLiveData.setValue(true);
    }
}
