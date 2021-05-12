package com.example.onlineshop.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.onlineshop.Network.NetworkParams;
import com.example.onlineshop.Network.retrofit.ShopService;
import com.example.onlineshop.Network.retrofit.RetrofitInstance;
import com.example.onlineshop.model.Category;
import com.example.onlineshop.model.Product;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Headers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductRepository {

    private static final String TAG = "ProductRepository";

    private ShopService mProductService;
    private ShopService mCategoryService;
    private ShopService mProductWithId;

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
    private MutableLiveData<Integer> mPerPage = new MutableLiveData<>();

    private MutableLiveData<Product> mProductItemLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Product>> mRelatedItemsLiveData = new MutableLiveData<>();

    public MutableLiveData<Product> getProductItemLiveData() {
        return mProductItemLiveData;
    }

    public MutableLiveData<List<Product>> getRelatedItemsLiveData() {
        return mRelatedItemsLiveData;
    }

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

    public MutableLiveData<Integer> getPerPage() {
        return mPerPage;
    }

    public static ProductRepository getInstance(){
        if (sInstance == null)
            sInstance = new ProductRepository();
        return sInstance;
    }

    private ProductRepository(){
        mProductService = RetrofitInstance.getProductInstance().create(ShopService.class);
        mCategoryService = RetrofitInstance.getCategoryInstance().create(ShopService.class);
        mProductWithId = RetrofitInstance.getProductWithId().create(ShopService.class);
    }

    public void fetchProductsAsync(int page, int idCategory){
        if (page == 1)
            mProducts = new ArrayList<>();

        Call<List<Product>> call = mProductService.listProductItems(
                NetworkParams.getProductsOptions(page, idCategory));
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                Headers headerList = response.headers();
                for (int i = 0; i < headerList.size(); i++) {
                    int totalPage = Integer.parseInt(headerList.get("X-WP-TotalPages"));
                    Log.e(TAG, "the total products" + totalPage);
                    mPageCount.setValue(totalPage);
                }

                List<Product> items = response.body();
                mProducts.addAll(items);
                mListProductLiveData.setValue(items);
                mCategoryItemId.setValue(idCategory);
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.e(TAG, t.getMessage(), t);
            }
        });

    }

    public void fetchCategoriesAsync(int page){
        Call<List<Category>> call = mCategoryService.listCategoryItems(
                NetworkParams.getCategoryOptions(page));
        call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                Headers headerList = response.headers();
                for (int i = 0; i < headerList.size(); i++) {
                    int totalPage = Integer.parseInt(headerList.get("X-WP-TotalPages"));

                    mPageCount.setValue(totalPage);
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

    public void fetchTotalProducts() {
        Call<List<Product>> call = mProductService.listProductItems(
                NetworkParams.getTotalProductsOptions());
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                Headers headerList = response.headers();
                for (int i = 0; i < headerList.size(); i++) {
                    int perPage = Integer.parseInt(headerList.get("X-WP-Total"));
                    mPerPage.setValue(perPage);
                }
            }
            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.e(TAG, t.getMessage(), t);
            }
        });
    }

    public void fetchNewestProductsList(int perPage){

        Call<List<Product>> call =
                mProductService.listProductItems(NetworkParams.getNewestProducts(perPage));
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

    public void fetchRatedProductsList(int perPage){
        Call<List<Product>> call =
                mProductService.listProductItems(NetworkParams.getRatedProducts(perPage));
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

    public void fetchVisitedProductsList(int perPage){
        Call<List<Product>> call =
                mProductService.listProductItems(NetworkParams.getVisitedProducts(perPage));
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

    public void fetchProductWithId(int id) {

        Call<Product> call = mProductWithId.getProduct(
                id, NetworkParams.getTotalProductsOptions());
        call.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                mProductItemLiveData.setValue(response.body());
            }
            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                Log.e(TAG, t.getMessage(), t);
            }
        });
    }

    public void fetchRelatedProducts(List<Integer> relatedProductsId) {

        List<Product> relatedItems = new ArrayList<>();
        for (int i = 0; i < relatedProductsId.size(); i++) {
            Call<Product> call = mProductWithId.getProduct(
                    relatedProductsId.get(i), NetworkParams.getTotalProductsOptions());
            call.enqueue(new Callback<Product>() {
                @Override
                public void onResponse(Call<Product> call, Response<Product> response) {
                    relatedItems.add(response.body());
                    Log.e(TAG, "related item is: " + response.body().getName());
                }

                @Override
                public void onFailure(Call<Product> call, Throwable t) {

                }
            });
        }
        mRelatedItemsLiveData.setValue(relatedItems);
    }

}
