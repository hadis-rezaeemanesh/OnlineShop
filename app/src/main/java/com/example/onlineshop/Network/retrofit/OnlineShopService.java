package com.example.onlineshop.Network.retrofit;

import com.example.onlineshop.model.Category;
import com.example.onlineshop.model.Product;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface OnlineShopService {

    @GET("products/")
    Call<List<Product>> listProductItems(@QueryMap Map<String, String> options);

    @GET("products/categories/")
    Call<List<Category>> listCategoryItems(@QueryMap Map<String, String> options);

    @GET("products")
    Call<List<Product>> getAllProducts(
            @Query("orderby") String type,
            @Query("per_page") String perpage);

}
