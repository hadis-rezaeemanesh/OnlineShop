package com.example.onlineshop.view.activity;

import androidx.fragment.app.Fragment;

import com.example.onlineshop.view.fragment.CategoryFragment;

public class MainActivity extends SingleFragmentActivity {
    @Override
    public Fragment createFragment() {
        return CategoryFragment.newInstance();
    }


}