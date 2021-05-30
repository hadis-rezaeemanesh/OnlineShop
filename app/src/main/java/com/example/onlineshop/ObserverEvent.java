package com.example.onlineshop;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;

import androidx.lifecycle.Observer;

public abstract class ObserverEvent<T> implements Observer<T> {

    private LifecycleOwner mLifecycleOwner;
    private LiveData mLiveData;

    public ObserverEvent(LifecycleOwner lifecycleOwner, LiveData liveData) {
        mLifecycleOwner = lifecycleOwner;
        mLiveData = liveData;
    }


    public void onChanged(T t) {
        mLiveData.removeObservers(mLifecycleOwner);
    }
}
