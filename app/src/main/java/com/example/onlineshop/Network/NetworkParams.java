package com.example.onlineshop.Network;

import java.util.HashMap;
import java.util.Map;

public class NetworkParams {

    public static final String BASE_URL = "https://woocommerce.maktabsharif.ir/wp-json/wc/v3/";
    public static final String CONSUMER_KEY = "ck_1a8895c40ccaf51ca20c335651ca1341b570232d";
    public static final String CONSUMER_SECRET = "cs_f984c71c9b18c036ba3ae325e5c92e26aaa7c78b";


    public static final Map<String, String > options = new HashMap<String, String>(){{
        put("consumer_key", CONSUMER_KEY);
        put("consumer_secret", CONSUMER_SECRET);
    }};


    public static Map<String, String> getProductsOptions(int page, int categoryId) {
        Map<String, String> productOptions = new HashMap<>();
        productOptions.putAll(options);
        productOptions.put("page", String.valueOf(page));
        productOptions.put("category", String.valueOf(categoryId));
        return productOptions;
    }

    public static Map<String, String> getCategoryOptions(int page) {
        Map<String, String> categoryOptions = new HashMap<>();
        categoryOptions.putAll(options);
        categoryOptions.put("page", String.valueOf(page));
        return categoryOptions;
    }



}
