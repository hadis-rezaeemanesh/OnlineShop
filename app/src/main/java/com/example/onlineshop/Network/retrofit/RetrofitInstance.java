package com.example.onlineshop.Network.retrofit;

import com.example.onlineshop.Network.NetworkParams;

import retrofit2.Retrofit;

public class RetrofitInstance {

    public static Retrofit getInstance(){
        return new Retrofit.Builder()
                .baseUrl(NetworkParams.BASE_URL)
                .build();
    }

}
