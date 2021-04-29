package com.example.onlineshop.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.onlineshop.Network.NetworkParams;
import com.example.onlineshop.Network.retrofit.OnlineShopService;
import com.example.onlineshop.Network.retrofit.RetrofitInstance;
import com.example.onlineshop.model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProductRepository {

    private static final String TAG = "ProductRepository";
    private OnlineShopService mService;
    private static ProductRepository sInstance;
    private MutableLiveData<List<Product>> mListProductLiveData = new MutableLiveData<>();

    public MutableLiveData<List<Product>> getListProductLiveData() {
        return mListProductLiveData;
    }

    public static ProductRepository getInstance(){
        if (sInstance == null)
            sInstance = new ProductRepository();
        return sInstance;
    }

    public ProductRepository(){
        Retrofit retrofit = RetrofitInstance.getInstance();
        mService = retrofit.create(OnlineShopService.class);
    }

    public void fetchItemsAsync(){
        Call<List<Product>> call = mService.listItems(NetworkParams.options);
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                List<Product> items = response.body();
                mListProductLiveData.setValue(items);
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.e(TAG, t.getMessage(), t);
            }
        });

    }

   /* public interface Callbacks {
        void onItemResponse(List<Product> items);
    }*/
}
