package com.example.onlineshop.model;

import java.util.Date;
import java.util.List;

public class Product {
    private int mId;
    private String mName;
    private String mPrice;
    private String mUrl;
    private int mRate;
    private Date mCreatedDate;
    private String mDescription;
    private List<String> mImages;
    private List<Integer> mCategoriesId;
    private List<Integer> mRelatedIds;
    private int mCountInCart = 0;

    public Product() {
    }

    public Product(int id, String name, String price, String url, int rate,
                   Date createdDate, List<String> images, List<Integer> categoriesId,
                   String description, List<Integer> relatedIds) {
        mId = id;
        mName = name;
        mPrice = price;
        mUrl = url;
        mRate = rate;
        mCreatedDate = createdDate;
        mImages = images;
        mCategoriesId = categoriesId;
        mDescription = description;
        mRelatedIds = relatedIds;
    }

    public int getRate() {
        return mRate;
    }

    public void setRate(int rate) {
        mRate = rate;
    }

    public Date getCreatedDate() {
        return mCreatedDate;
    }

    public void setCreatedDate(Date createdDate) {
        mCreatedDate = createdDate;
    }

    public List<String> getImages() {
        return mImages;
    }

    public void setImages(List<String> images) {
        mImages = images;
    }

    public List<Integer> getCategoriesId() {
        return mCategoriesId;
    }

    public void setCategoriesId(List<Integer> categoriesId) {
        mCategoriesId = categoriesId;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
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

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public List<Integer> getRelatedIds() {
        return mRelatedIds;
    }

    public void setRelatedIds(List<Integer> relatedIds) {
        mRelatedIds = relatedIds;
    }

    public int getCountInCart() {
        return mCountInCart;
    }

    public void setCountInCart(int mCountInCart) {
        this.mCountInCart = mCountInCart;
    }
}
