package com.example.onlineshop.Network;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

public class NetworkParams {

    public static final String TAG = "networkParams";

    public static final String BASE_URL = "https://woocommerce.maktabsharif.ir/wp-json/wc/v3/";
    public static final String CONSUMER_KEY = "ck_1a8895c40ccaf51ca20c335651ca1341b570232d";
    public static final String CONSUMER_SECRET = "cs_f984c71c9b18c036ba3ae325e5c92e26aaa7c78b";


    public static final Map<String, String > options = new HashMap<String, String>(){{
        put("consumer_key", CONSUMER_KEY);
        put("consumer_secret", CONSUMER_SECRET);
    }};


    public static Map<String, String> getProductsOptions(int page, int idCategory) {
        Map<String, String> productOptions = new HashMap<>();
        productOptions.putAll(options);
        productOptions.put("page", String.valueOf(page));
        productOptions.put("category", String.valueOf(idCategory));
        return productOptions;
    }

    public static Map<String, String> getCategoryOptions(int page) {
        Map<String, String> categoryOptions = new HashMap<>();
        categoryOptions.putAll(options);
        categoryOptions.put("page", String.valueOf(page));
        return categoryOptions;
    }

    public static Map<String, String > getNewestProducts(int perPage){
        Log.d(TAG, "getNewestProducts: ");
        Map<String , String > productsOptions = new HashMap<>();
        productsOptions.putAll(options);
        productsOptions.put("orderby", "date");
        productsOptions.put("per_page", String.valueOf(perPage));
        return productsOptions;
    }

    public static Map<String, String > getRatedProducts(int perPage){
        Map<String , String > productsOptions = new HashMap<>();
        productsOptions.putAll(options);
        productsOptions.put("orderby", "rating");
        productsOptions.put("per_page", String.valueOf(perPage));
        return productsOptions;
    }

    public static Map<String, String > getVisitedProducts(int perPage){
        Map<String , String > productsOptions = new HashMap<>();
        productsOptions.putAll(options);
        productsOptions.put("orderby", "popularity");
        productsOptions.put("per_page", String.valueOf(perPage));
        return productsOptions;
    }

    public static Map<String, String> getTotalProductsOptions() {
        Map<String, String> totalOptions = new HashMap<>();
        totalOptions.putAll(options);
        return totalOptions;
    }

    public static Map<String, String> getSearchOptions(String query){
        Map<String, String> searchOptions = new HashMap<>();
        searchOptions.putAll(options);
        searchOptions.put("search", query);
        return searchOptions;
    }

    public static Map<String, String> getPerPageForCategory(int categoryId) {
        Map<String, String> perPageOptions = new HashMap<>();
        perPageOptions.putAll(options);
        perPageOptions.put("category", String.valueOf(categoryId));
        return perPageOptions;
    }

    public static Map<String, String> getCustomer(String email){
        Map<String, String> customerOptions = new HashMap<>();
        customerOptions.putAll(options);
        customerOptions.put("email", email);
        return customerOptions;
    }

    public static Map<String, String> getReviews(int productId){
        Map<String, String> reviewOptions = new HashMap<>();
        reviewOptions.putAll(options);
        reviewOptions.put("product", String.valueOf(productId));
        Log.e("productReview", String.valueOf(productId));
        return reviewOptions;
    }

}
