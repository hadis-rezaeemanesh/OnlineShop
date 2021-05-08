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


    public static Map<String, String> getProductsOptions() {
        Map<String, String> productOptions = new HashMap<>();
        productOptions.putAll(options);
        productOptions.put("per_page", "100" );
        return productOptions;
    }

    public static Map<String, String> getCategoryOptions() {
        Map<String, String> categoryOptions = new HashMap<>();
        categoryOptions.putAll(options);
        categoryOptions.put("per_page", "100");
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



}
