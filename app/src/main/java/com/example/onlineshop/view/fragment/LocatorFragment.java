package com.example.onlineshop.view.fragment;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.Toast;

import com.example.onlineshop.R;
import com.example.onlineshop.databinding.FragmentLocatorBinding;
import com.example.onlineshop.viewModel.AccountViewModel;
import com.example.onlineshop.viewModel.LocatorViewModel;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class LocatorFragment extends Fragment {

    public static final int REQUEST_CODE_PERMISSION_LOCATION = 0;
    private FragmentLocatorBinding mBinding;
    private LocatorViewModel mViewModel;

    private GoogleMap mMap;

    public LocatorFragment() {
        // Required empty public constructor
    }

    public static LocatorFragment newInstance() {
        LocatorFragment fragment = new LocatorFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(LocatorViewModel.class);
        mViewModel.getLocationLiveData().observe(this, new Observer<Location>() {
            @Override
            public void onChanged(Location location) {
                updateUI();
            }
        });
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_locator,
                container,
                false);
        initToolbar();

        SupportMapFragment supportMapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.fragment_map);
        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull GoogleMap googleMap) {
                mMap = googleMap;
                updateUI();
            }
        });

        return mBinding.getRoot();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_locator, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_item_location:
                if (hasLocationAccess()){
                    requestLocation();

                }else {
                    //request location access permission
                    requestLocationAccessPermission();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    private boolean hasLocationAccess(){
        boolean isFineLocation = ContextCompat.checkSelfPermission(
                getContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
        boolean isCoarseLocation = ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED;
        return isFineLocation && isCoarseLocation;
    }

    private void requestLocationAccessPermission(){
        String[] permissions = new String[]{
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
        };
        requestPermissions(permissions, REQUEST_CODE_PERMISSION_LOCATION);
    }

    @Override
    public void onRequestPermissionsResult(
            int requestCode,
            @NonNull String[] permissions,
            @NonNull int[] grantResults) {
        switch (requestCode){
            case REQUEST_CODE_PERMISSION_LOCATION:
                if (grantResults == null || grantResults.length == 0)
                    return;
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    // get the location
                    requestLocation();
                else
                    Toast.makeText(
                            getContext(),
                            "We do not have the location permission",
                            Toast.LENGTH_LONG).show();

                    return;
        }
    }

    private void initToolbar() {
        ((AppCompatActivity) getActivity()).setSupportActionBar(mBinding.locatorToolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(null);
    }

    private void requestLocation(){
        if (!hasLocationAccess())
            return;
        mViewModel.requestLocation();
    }

    private void updateUI(){
        Location location = mViewModel.getLocationLiveData().getValue();
        if (location == null || mMap == null)
            return;

        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions().position(latLng).title("My Location");
        mMap.addMarker(markerOptions);

        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLng(latLng);
        mMap.animateCamera(cameraUpdate);
    }


}