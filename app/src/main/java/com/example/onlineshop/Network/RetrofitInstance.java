package com.example.onlineshop.Network;

import retrofit2.Retrofit;

public class RetrofitInstance {

    public static Retrofit getInstance(){
        return new Retrofit.Builder()
                .baseUrl(NetworkParams.BASE_URL)
                .build();
    }

}
