package com.example.onlineshop.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.onlineshop.Network.NetworkParams;
import com.example.onlineshop.Network.retrofit.OnlineShopService;
import com.example.onlineshop.Network.retrofit.RetrofitInstance;
import com.example.onlineshop.model.Category;
import com.example.onlineshop.model.Product;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Headers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.Header;

public class ProductRepository {

    private static final String TAG = "ProductRepository";

    private OnlineShopService mProductService;
    private OnlineShopService mCategoryService;

    private static ProductRepository sInstance;

    private List<Product> mProducts;
    private List<Category> mCategories;

    private MutableLiveData<List<Product>> mListProductLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Category>> mListCategoryLiveData = new MutableLiveData<>();

    private MutableLiveData<List<Product>> mNewestProductsLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Product>> mRatedProductsLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Product>> mVisitedProductsLiveData = new MutableLiveData<>();

    private MutableLiveData<Integer> mPageCount = new MutableLiveData<>();
    private MutableLiveData<Integer> mCategoryItemId = new MutableLiveData<>();

    public MutableLiveData<List<Product>> getListProductLiveData() {
        return mListProductLiveData;
    }

    public MutableLiveData<List<Category>> getListCategoryLiveData() {
        return mListCategoryLiveData;
    }

    public MutableLiveData<List<Product>> getNewestProductsLiveData() {
        return mNewestProductsLiveData;
    }

    public MutableLiveData<List<Product>> getRatedProductsLiveData() {
        return mRatedProductsLiveData;
    }

    public MutableLiveData<List<Product>> getVisitedProductsLiveData() {
        return mVisitedProductsLiveData;
    }

    public MutableLiveData<Integer> getPageCount() {
        return mPageCount;
    }

    public MutableLiveData<Integer> getCategoryItemId() {
        return mCategoryItemId;
    }

    public static ProductRepository getInstance(){
        if (sInstance == null)
            sInstance = new ProductRepository();
        return sInstance;
    }

    private ProductRepository(){
        mProductService = RetrofitInstance.getProductInstance().create(OnlineShopService.class);
        mCategoryService = RetrofitInstance.getCategoryInstance().create(OnlineShopService.class);
    }

    public void fetchProductsAsync(){


        Call<List<Product>> call = mProductService.listProductItems(
                NetworkParams.getProductsOptions());
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {

                List<Product> items = response.body();
                mListProductLiveData.setValue(mProducts);
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.e(TAG, t.getMessage(), t);
            }
        });

    }

    public void fetchCategoriesAsync(){
        Call<List<Category>> call = mCategoryService.listCategoryItems(
                NetworkParams.getCategoryOptions());
        call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                List<Category> items = response.body();
                mListCategoryLiveData.setValue(items);
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Log.e(TAG, t.getMessage(), t);
            }
        });

    }

    public void fetchNewestProductsList(){

        Call<List<Product>> call =
                mProductService.listProductItems(NetworkParams.getNewestProducts());
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                List<Product> items = response.body();
                mNewestProductsLiveData.setValue(items);
                Log.d(TAG, "onResponse: " + call);
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.e(TAG, t.getMessage(), t);

            }
        });
    }

    public void fetchRatedProductsList(){
        Call<List<Product>> call =
                mProductService.listProductItems(NetworkParams.getRatedProducts());
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                List<Product> items = response.body();
                mRatedProductsLiveData.setValue(items);
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.e(TAG, t.getMessage(), t);

            }
        });
    }

    public void fetchVisitedProductsList(){
        Call<List<Product>> call =
                mProductService.listProductItems(NetworkParams.getVisitedProducts());
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                List<Product> items = response.body();
                mVisitedProductsLiveData.setValue(items);
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.e(TAG, t.getMessage(), t);

            }
        });
    }

}
