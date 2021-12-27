package com.example.onlineshop.viewModel;

import android.annotation.SuppressLint;
import android.app.Application;
import android.location.Location;
import android.os.Looper;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

public class LocatorViewModel extends AndroidViewModel {

    private FusedLocationProviderClient mFusedLocationClient;
    private MutableLiveData<Location> mLocationLiveData = new MutableLiveData<>();

    public LocatorViewModel(@NonNull Application application) {
        super(application);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(getApplication());
    }

    public MutableLiveData<Location> getLocationLiveData() {
        return mLocationLiveData;
    }

    @SuppressLint("MissingPermission")
    public void requestLocation(){
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setNumUpdates(1);
        locationRequest.setInterval(0);

        LocationCallback locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(@NonNull LocationResult locationResult) {
                super.onLocationResult(locationResult);
                Location location = locationResult.getLocations().get(0);
                mLocationLiveData.setValue(location);
            }
        };

        mFusedLocationClient.requestLocationUpdates(
                locationRequest,
                locationCallback,
                Looper.getMainLooper());
    }
}
