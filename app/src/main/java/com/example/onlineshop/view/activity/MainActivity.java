package com.example.onlineshop.view.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.onlineshop.R;
import com.example.onlineshop.databinding.ActivityOnlineShopBinding;
import com.example.onlineshop.view.fragment.CategoryFragment;
import com.example.onlineshop.view.fragment.HomePageFragment;
import com.example.onlineshop.view.fragment.ProductListFragment;
import com.example.onlineshop.view.fragment.ToolbarFragment;
import com.example.onlineshop.viewModel.ProductViewModel;

public class MainActivity extends SingleFragmentActivity {
    @Override
    public Fragment createFragment() {
        return CategoryFragment.newInstance();
    }

}