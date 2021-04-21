package com.example.onlineshop.Network;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface OnlineShopService {

    @GET("products?")
    Call<List<Response>> listItems(@QueryMap Map<String, String> options);

}
