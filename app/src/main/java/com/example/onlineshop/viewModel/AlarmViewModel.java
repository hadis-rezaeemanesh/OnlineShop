package com.example.onlineshop.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.onlineshop.work.PollWorker;

public class AlarmViewModel extends AndroidViewModel {
    private MutableLiveData<Boolean> mToggleClicked  = new MutableLiveData<>();


    public AlarmViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<Boolean> getToggleClicked() {
        return mToggleClicked;
    }

    public void togglePolling() {
        boolean isOn = PollWorker.isWorkEnqueued(getApplication());
        PollWorker.enqueueWork(getApplication(), !isOn);
        mToggleClicked.setValue(true);
    }
}
