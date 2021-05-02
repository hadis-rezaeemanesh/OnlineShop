package com.example.onlineshop.Network.retrofit;

import com.example.onlineshop.Network.NetworkParams;
import com.example.onlineshop.model.Category;
import com.example.onlineshop.model.Product;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    public static Retrofit getProductInstance(){
        return new Retrofit.Builder()
                .baseUrl(NetworkParams.BASE_URL)
                .addConverterFactory(createGsonConverter(
                        new TypeToken<List<Product>>() {}.getType(),
                        new GetProductDeserializer()))
                .build();
    }

    public static Retrofit getCategoryInstance(){
        return new Retrofit.Builder()
                .baseUrl(NetworkParams.BASE_URL)
                .addConverterFactory(createGsonConverter(
                        new TypeToken<List<Category>>() {}.getType(),
                        new GetCategoryDeserializer()
                ))
                .build();
    }
    private static Converter.Factory createGsonConverter(Type type, Object typeAdapter) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(type, typeAdapter);
        Gson gson = gsonBuilder.create();

        return GsonConverterFactory.create(gson);
    }

}
