package com.example.onlineshop.model;

public class Category {
    private String mName;
    private int mId;
    private int mCount;
    private String mImage;

    public Category(String name, int id, int count, String image) {
        mName = name;
        mId = id;
        mCount = count;
        mImage = image;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public int getCount() {
        return mCount;
    }

    public void setCount(int count) {
        mCount = count;
    }

    public String getImage() {
        return mImage;
    }

    public void setImage(String image) {
        mImage = image;
    }
}
