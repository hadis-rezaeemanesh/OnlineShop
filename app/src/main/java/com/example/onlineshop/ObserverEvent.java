package com.example.onlineshop;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;

import java.util.Observer;

public abstract class ObserverEvent<T> implements Observer {

    private LifecycleOwner mLifecycleOwner;
    private LiveData mLiveData;

    public ObserverEvent(LifecycleOwner lifecycleOwner, androidx.lifecycle.LiveData liveData) {
        mLifecycleOwner = lifecycleOwner;
        mLiveData = liveData;
    }


    public void onChanged(T t) {
        mLiveData.removeObservers(mLifecycleOwner);
    }
}
