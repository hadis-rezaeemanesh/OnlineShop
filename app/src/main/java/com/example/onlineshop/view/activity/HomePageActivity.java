package com.example.onlineshop.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;

import com.example.onlineshop.R;
import com.example.onlineshop.adapter.CategoryAdapter;
import com.example.onlineshop.databinding.ActivityOnlineShopBinding;
import com.example.onlineshop.model.Category;
import com.example.onlineshop.view.fragment.HomePageFragment;
import com.example.onlineshop.view.fragment.ProductListFragment;
import com.example.onlineshop.view.fragment.ToolbarFragment;
import com.example.onlineshop.viewModel.CategoryViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

public class HomePageActivity extends SingleFragmentActivity {

    public static final String TAG = "homePageActivity";
    private ActivityOnlineShopBinding mBinding;
    private CategoryViewModel mViewModel;

    @Override
    public Fragment createFragment() {
        return HomePageFragment.newInstance();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        addFragment();

      /*  mBinding.navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home_menu:
                        addFragment();
                        Log.d(TAG, "Home navigation");
                        break;
                    case R.id.category_menu:
                        addFragment();
                        Log.d(TAG, "category list clicked");
                        break;
                }
                mBinding.drawerLayout.closeDrawers();
                Log.d(TAG, "menu is closed");
                return true;
            }
        });*/

    }
    public void openNavigationView() {
        mBinding.drawerLayout.openDrawer(Gravity.RIGHT);
    }

    private void addFragment() {
        Log.d(TAG, "add fragment");
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.toolbar_container, ToolbarFragment.newInstance())
                .commit();
    }



}