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

    private MutableLiveData<Integer> mPageCount = new MutableLiveData<>();
    private MutableLiveData<Integer> mCategoryItemId = new MutableLiveData<>();

    public MutableLiveData<List<Product>> getListProductLiveData() {
        return mListProductLiveData;
    }

    public MutableLiveData<List<Category>> getListCategoryLiveData() {
        return mListCategoryLiveData;
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

    public void fetchProductsAsync(int page, int idCategory){
        if (page == 1)
            mProducts = new ArrayList<>();

        Call<List<Product>> call = mProductService.listProductItems(
                NetworkParams.getProductsOptions(page, idCategory));
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                Headers headers = response.headers();
                for (int i = 0; i < headers.size(); i++) {
                    int totalPage = Integer.parseInt(headers.get("X-WP-TotalPages"));
                    mPageCount.setValue(totalPage);
                }
                List<Product> items = response.body();
                mProducts.addAll(items);
                mListProductLiveData.setValue(mProducts);
                mCategoryItemId.setValue(idCategory);
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.e(TAG, t.getMessage(), t);
            }
        });

    }

    public void fetchCategoriesAsync(int page){
        Call<List<Category>> call = mProductService.listCategoryItems(
                NetworkParams.getCategoryOptions(page));
        call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                Headers headers = response.headers();
                for (int i = 0; i <headers.size() ; i++) {
                    int totalPage = Integer.parseInt(headers.get("X-WP-TotalPages"));
                    mPageCount.setValue((totalPage));

                }
                List<Category> items = response.body();
                mCategories.addAll(items);
                mListCategoryLiveData.setValue(mCategories);
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Log.e(TAG, t.getMessage(), t);
            }
        });

    }

}
