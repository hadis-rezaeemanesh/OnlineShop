package com.example.onlineshop.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.onlineshop.model.Customer;
import com.example.onlineshop.model.LineItem;
import com.example.onlineshop.model.Order;
import com.example.onlineshop.model.Product;
import com.example.onlineshop.repository.CustomerRepository;
import com.example.onlineshop.utilities.QueryPreferences;

import java.util.ArrayList;
import java.util.List;

public class OrderViewModel extends AndroidViewModel {

    private CustomerRepository mRepository;
    private LiveData<Customer> mCustomerLiveData;

    public OrderViewModel(@NonNull Application application) {
        super(application);
        mRepository = CustomerRepository.getInstance();
        mCustomerLiveData = mRepository.getCustomerLiveData();
    }

    public LiveData<Customer> getCustomerLiveData() {
        return mCustomerLiveData;
    }

    public String setTotalPrice() {
        int total = 0;
        List<Product> items = QueryPreferences.getCartProducts(getApplication());
        if (items != null) {
            for (int i = 0; i < items.size(); i++) {
                total += Integer.parseInt(items.get(i).getPrice());
            }
            return total + " تومان ";
        } else
            return "";
    }

    public void sendOrder(){
        List<Product> items = QueryPreferences.getCartProducts(getApplication());
        List<LineItem> lineItems = new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {
            LineItem item = new LineItem(items.get(i).getId(),1);
            lineItems.add(item);
        }
        Order order = new Order(QueryPreferences.getCustomerId(getApplication()), lineItems);
        mRepository.sendOrder(order);
    }
}
