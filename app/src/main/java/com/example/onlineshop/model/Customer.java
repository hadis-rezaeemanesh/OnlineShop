package com.example.onlineshop.model;

import com.google.gson.annotations.SerializedName;

public class Customer {

    @SerializedName("email")
    private String mEmail;

    @SerializedName("first_name")
    private String mFirstName;

    @SerializedName("last_name")
    private String mLastName;

    @SerializedName("username")
    private String mUserName;

    @SerializedName("billing")
    private com.example.onlineshop.model.Billing mBilling;

    @SerializedName("shipping")
    private com.example.onlineshop.model.Shipping mShipping;

    public String getEmail() {
        return mEmail;
    }

    public Customer(String email){
        mEmail = email;
    }

    public Customer(String email, String userName){
        mEmail = email;
        mUserName = userName;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String firstName) {
        mFirstName = firstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String lastName) {
        mLastName = lastName;
    }

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String userName) {
        mUserName = userName;
    }

    public com.example.onlineshop.model.Billing getBilling() {
        return mBilling;
    }

    public void setBilling(com.example.onlineshop.model.Billing billing) {
        mBilling = billing;
    }

    public com.example.onlineshop.model.Shipping getShipping() {
        return mShipping;
    }

    public void setShipping(com.example.onlineshop.model.Shipping shipping) {
        mShipping = shipping;
    }
}

