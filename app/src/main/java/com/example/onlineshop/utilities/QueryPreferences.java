package com.example.onlineshop.utilities;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.onlineshop.model.Product;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QueryPreferences {
    private static final String PREF_CART_PRODUCT = "cartProduct";
    private static final String PREF_PRODUCT_ID = "productId";
    private static final String PREF_USER_EMAIL = "userEmail";
    private static final String PREF_USER_NAME = "userName";
    private static final String PREF_CUSTOMER_ID = "id";

    public static int getCustomerId(Context context){
        return getSharedPreferences(context).getInt(PREF_CUSTOMER_ID, 0);
    }

    public static void setCustomerId(Context context, Integer id){
        getSharedPreferences(context)
                .edit()
                .putInt(PREF_CUSTOMER_ID, id)
                .apply();
    }

    public static String getUserName(Context context){
        return getSharedPreferences(context).getString(PREF_USER_NAME, null );
    }

    public static void setUserName(Context context, String userName){
        getSharedPreferences(context)
                .edit()
                .putString(PREF_USER_NAME, userName)
                .apply();
    }

    public static String getUserEmail(Context context){
        return getSharedPreferences(context).getString(PREF_USER_EMAIL, null);
    }

    public static void setUserEmail(Context context, String email){
        getSharedPreferences(context)
                .edit()
                .putString(PREF_USER_EMAIL, email)
                .apply();
    }

    public static int getProductId(Context context){
        return getSharedPreferences(context).getInt(PREF_PRODUCT_ID, 0);
    }
    public static void  setProductId(Context context, int productId){
        getSharedPreferences(context)
                .edit()
                .putInt(PREF_PRODUCT_ID, productId)
                .apply();
    }
    public static void setCartProducts(Context context, List<Product> productItems){

        Gson gson = new Gson();
        String jsonProducts = gson.toJson(productItems);

        getSharedPreferences(context)
                .edit()
                .putString(PREF_CART_PRODUCT, jsonProducts)
                .apply();
    }

    public static void addCartProduct(Context context, Product item){
        List<Product> products = getCartProducts(context);
        if(products == null)
            products = new ArrayList<>();
        products.add(item);
        setCartProducts(context, products);
    }

    public static void removeCartProduct(Context context, Product item){
        ArrayList<Product> products = getCartProducts(context);
        if(products != null){
            products.remove(products);
            setCartProducts(context, products);
        }
    }

    public static ArrayList<Product> getCartProducts(Context context){

        if(getSharedPreferences(context).contains(PREF_CART_PRODUCT)){
            String jsonProducts = getSharedPreferences(context).getString(PREF_CART_PRODUCT, null);
            Gson gson = new Gson();
            Product[] productItems = gson.fromJson(jsonProducts, Product[].class);
            return new ArrayList<>(Arrays.asList(productItems));
        }
        return null;
    }

    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
    }
}
