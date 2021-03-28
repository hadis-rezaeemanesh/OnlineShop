package com.example.onlineshop.model;

public class Product {
    private String mName;
    private String mPrice;
    private String mUrl;

    public Product() {
    }

    public Product(String name, String price, String url) {
        mName = name;
        mPrice = price;
        mUrl = url;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getPrice() {
        return mPrice;
    }

    public void setPrice(String price) {
        mPrice = price;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }
}
