package com.example.onlineshop.Network.retrofit;

import com.example.onlineshop.Network.NetworkParams;
import com.example.onlineshop.model.Category;
import com.example.onlineshop.model.Customer;
import com.example.onlineshop.model.Product;
import com.example.onlineshop.model.Review;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    private static Retrofit mRetrofitInstance;
    public static final String BASE_URL = "https://woocommerce.maktabsharif.ir/wp-json/wc/v3/";
    public static final String USER_NAME = "ck_1a8895c40ccaf51ca20c335651ca1341b570232d";
    public static final String PASSWORD = "cs_f984c71c9b18c036ba3ae325e5c92e26aaa7c78b";

    public static Retrofit getRetrofit() {
        if (mRetrofitInstance == null) {
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(new BasicAuthInterceptor(USER_NAME, PASSWORD))
                    .build();

            mRetrofitInstance = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }


        return mRetrofitInstance;
    }

    private static class BasicAuthInterceptor implements Interceptor {

        private String credentials;

        public BasicAuthInterceptor(String user, String password) {
            this.credentials = Credentials.basic(user, password);
        }

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Request authenticatedRequest = request.newBuilder()
                    .header("Authorization", credentials).build();
            return chain.proceed(authenticatedRequest);
        }

    }


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

    public static Retrofit getProductWithId(){
        return new Retrofit.Builder()
                .baseUrl(NetworkParams.BASE_URL)
                .addConverterFactory(createGsonConverter(
                        Product.class,
                        new GetProductItemDeserializer()
                ))
                .build();
    }

    public static Retrofit getCustomerInstance(){
        return new Retrofit.Builder()
                .baseUrl(NetworkParams.BASE_URL)
                .addConverterFactory(createGsonConverter(
                        new TypeToken<List<Customer>>() {}.getType(),
                        new GetCustomerDeserializer()
                ))
                .build();
    }

    public static Retrofit getReviewInstance(){
        return new Retrofit.Builder()
                .baseUrl(NetworkParams.BASE_URL)
                .addConverterFactory(createGsonConverter(
                        new TypeToken<List<Review>>() {}.getType(),
                        new GetReviewDeserializer()
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
