package com.example.onlineshop.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.onlineshop.Network.NetworkParams;
import com.example.onlineshop.Network.retrofit.RetrofitInstance;
import com.example.onlineshop.Network.retrofit.ShopService;
import com.example.onlineshop.model.Customer;
import com.example.onlineshop.model.Order;
import com.example.onlineshop.model.Review;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomerRepository {

    private static final String TAG = "CustomerRepository";
    private static CustomerRepository sInstance;
    private ShopService mShopServiceCustomer;
    private ShopService mShopServiceReview;
    private MutableLiveData<Customer> mCustomerLiveData = new MutableLiveData<>();
    private MutableLiveData<Boolean> mRegisterLiveData = new MutableLiveData<>();
    private MutableLiveData<Customer> mSearchEmailLiveData = new MutableLiveData<>();
    private MutableLiveData<Order> mOrderLiveData = new MutableLiveData<>();
    private MutableLiveData<Boolean> mSendReview = new MutableLiveData<>();
    private MutableLiveData<List<Review>> mReviewLiveData = new MutableLiveData<>();

    public static CustomerRepository getInstance() {
        if (sInstance == null)
            sInstance = new CustomerRepository();
        return sInstance;
    }

    private CustomerRepository() {
        mShopServiceCustomer = RetrofitInstance.getCustomerInstance().create(ShopService.class);
        mShopServiceReview = RetrofitInstance.getReviewInstance().create(ShopService.class);
    }

    public MutableLiveData<List<Review>> getReviewLiveData() {
        return mReviewLiveData;
    }

    public MutableLiveData<Boolean> getSendReview() {
        return mSendReview;
    }

    public MutableLiveData<Customer> getCustomerLiveData() {
        return mCustomerLiveData;
    }

    public MutableLiveData<Boolean> getRegisterLiveData() {
        return mRegisterLiveData;
    }

    public MutableLiveData<Customer> getSearchEmailLiveData() {
        return mSearchEmailLiveData;
    }

    public MutableLiveData<Order> getOrderLiveData() {
        return mOrderLiveData;
    }

    public void createCustomer(Customer customer) {
        Call<Customer> call = mShopServiceCustomer.createCustomer(
                "https://woocommerce.maktabsharif.ir/wp-json/wc/v3/customers",
                customer,
                NetworkParams.CONSUMER_KEY,
                NetworkParams.CONSUMER_SECRET
        );
        call.enqueue(new Callback<Customer>() {
            @Override
            public void onResponse(Call<Customer> call, Response<Customer> response) {

                Log.e(TAG, "onResponse: " + response.code());
                if (response.isSuccessful()) {
                    mRegisterLiveData.setValue(true);
//                    mCustomerLiveData.setValue(response.body());
                    Log.e(TAG, "onResponse: " + response.body().getEmail());
                } else {
                    mRegisterLiveData.setValue(false);
                    Log.e(TAG, response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<Customer> call, Throwable t) {
                Log.e("postCustomer", t.getMessage());
            }
        });
    }

    public void searchCustomer(String email) {
        Call<List<Customer>> call =
                mShopServiceCustomer.getCustomer(NetworkParams.getCustomer(email));
        call.enqueue(new Callback<List<Customer>>() {
            @Override
            public void onResponse(Call<List<Customer>> call, Response<List<Customer>> response) {
                if(response.isSuccessful()) {
                    mCustomerLiveData.setValue(response.body().get(0));
                    Log.e(TAG, "searchEmail:" + response.body().get(0).getEmail());
                    mSearchEmailLiveData.setValue(response.body().get(0));
                }else
                    mSearchEmailLiveData.setValue(null);
            }

            @Override
            public void onFailure(Call<List<Customer>> call, Throwable t) {

            }
        });
    }


    public void sendOrder(Order order) {
        Call<Order> call = mShopServiceCustomer.sendOrder(
                "https://woocommerce.maktabsharif.ir/wp-json/wc/v3/orders",
                order,
                NetworkParams.CONSUMER_KEY,
                NetworkParams.CONSUMER_SECRET
        );
        call.enqueue(new Callback<Order>() {
            @Override
            public void onResponse(Call<Order> call, Response<Order> response) {
                if (response.isSuccessful()) {
                    mOrderLiveData.setValue(response.body());
                    Log.e(TAG,"post order: " + response.code());
                    Log.e(TAG,"post order: " + response.body().getBilling().getEmail());
                }else
                    mOrderLiveData.setValue(null);
            }

            @Override
            public void onFailure(Call<Order> call, Throwable t) {

            }
        });
    }

    public void sendReview(Review review){
        Call<Review> call = mShopServiceReview.sendReview(
                "https://woocommerce.maktabsharif.ir/wp-json/wc/v3/products/reviews",
                review,
                NetworkParams.CONSUMER_KEY,
                NetworkParams.CONSUMER_SECRET
        );
        call.enqueue(new Callback<Review>() {
            @Override
            public void onResponse(Call<Review> call, Response<Review> response) {
                if(response.isSuccessful())
                    mSendReview.setValue(true);
            }

            @Override
            public void onFailure(Call<Review> call, Throwable t) {

            }
        });
    }

    public void fetchProductReviews(int productId){
        Call<List<Review>> call =
                mShopServiceReview.getReviews(NetworkParams.getReviews(productId));
        call.enqueue(new Callback<List<Review>>() {
            @Override
            public void onResponse(Call<List<Review>> call, Response<List<Review>> response) {
                mReviewLiveData.setValue(response.body());

            }

            @Override
            public void onFailure(Call<List<Review>> call, Throwable t) {

            }
        });
    }
}
