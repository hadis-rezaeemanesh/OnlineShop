package com.example.onlineshop.view.activity;

import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;

import com.example.onlineshop.view.fragment.HomePageFragment;
import com.example.onlineshop.view.fragment.ProductListFragment;
import com.example.onlineshop.view.fragment.ToolbarFragment;

public class MainActivity extends SingleFragmentActivity {

    public static Intent newIntent(Context context){
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

    @Override
    public Fragment createFragment() {
        return ToolbarFragment.newInstance();
    }

}