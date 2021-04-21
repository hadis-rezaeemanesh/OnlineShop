package com.example.onlineshop.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.onlineshop.R;
import com.example.onlineshop.fragment.ProductListFragment;

public class MainActivity extends SingleFragmentActivity {

    public static Intent newIntent(Context context){
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

    @Override
    public Fragment createFragment() {
        return ProductListFragment.newInstance();
    }

}