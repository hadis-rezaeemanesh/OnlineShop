package com.example.onlineshop.Network.retrofit;

import com.example.onlineshop.model.Category;
import com.example.onlineshop.model.Customer;
import com.example.onlineshop.model.Order;
import com.example.onlineshop.model.Product;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface ShopService {

    @GET("products/")
    Call<List<Product>> listProductItems(@QueryMap Map<String, String> options);

    @GET("products/categories/")
    Call<List<Category>> listCategoryItems(@QueryMap Map<String, String> options);

    @GET("products/{id}")
    Call<Product> getProduct(
            @Path("id") Integer id,
            @QueryMap Map<String , String> options
    );


    @GET("products/?")
    Call<List<Product>> getAllProducts(
            @Query("orderby") String type ,
            @Query("per_page") String perpage);


    @GET("products/{id}/?")
    Call<Product> getProduct(@Path("id") String productId);

    @GET("products/categories/?per_page=100")
    Call<List<Category>> getAllCategories();


    @GET("customers")
    Call<List<Customer>> getCustomer(@QueryMap Map<String, String> options);

    @POST
    Call<Customer> createCustomer(
            @Url String url,
            @Body Customer customer,
            @Query("consumer_key") String consumerKey,
            @Query("consumer_secret") String consumerSecret
    );

    @POST
    Call<Order> sendOrder(
            @Url String url,
            @Body Order order,
            @Query("consumer_key") String consumerKey,
            @Query("consumer_secret") String consumerSecret
    );

}
