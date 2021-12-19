package com.example.onlineshop.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import android.os.Bundle;
import android.view.MenuItem;
import com.example.onlineshop.R;
import com.example.onlineshop.databinding.ActivityOnlineShopBinding;
import com.example.onlineshop.viewModel.CategoryViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class HomePageActivity extends AppCompatActivity {

    public static final String TAG = "homePageActivity";
    private ActivityOnlineShopBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(
                this,
                R.layout.activity_online_shop);
        setNavController();
    }

    private void setNavController() {
        NavController navController = Navigation.findNavController(this, R.id.nav_home_fragment);
        NavigationUI.setupWithNavController(mBinding.bottomNavigation, navController);

        mBinding.bottomNavigation.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.categoryFragment:
                                Navigation.findNavController(
                                        HomePageActivity.this,
                                        R.id.nav_home_fragment).navigate(R.id.categoryFragment);
                                break;
                            case R.id.homePageFragment:
                                Navigation.findNavController(
                                        HomePageActivity.this,
                                        R.id.nav_home_fragment).navigate(R.id.homePageFragment);
                                break;
                            case R.id.cartFragment:
                                Navigation.findNavController(
                                        HomePageActivity.this,
                                        R.id.nav_home_fragment).navigate(R.id.cartFragment);
                                break;
                            case R.id.accountFragment:
                                Navigation.findNavController(HomePageActivity.this,
                                        R.id.nav_home_fragment).navigate(R.id.accountFragment);
                                break;
                        }
                        return true;
                    }
                });
    }

}