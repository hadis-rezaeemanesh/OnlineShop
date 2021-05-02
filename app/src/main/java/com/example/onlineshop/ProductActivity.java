package com.example.onlineshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.onlineshop.databinding.ActivityOnlineShopBinding;
import com.example.onlineshop.model.Product;
import com.example.onlineshop.view.fragment.ProductListFragment;
import com.example.onlineshop.viewModel.ProductViewModel;

import java.util.List;
import java.util.Observable;

public class ProductActivity extends AppCompatActivity {

    public static final String EXTRA_LIST_CATEGORY_NAME = "com.example.onlineshop.listCategoryName";
    private String mCategoryName;
    private ActivityOnlineShopBinding mOnlineShopBinding;
    private ProductViewModel mProductViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mOnlineShopBinding = DataBindingUtil.setContentView(
                this,
                R.layout.activity_online_shop);
        mCategoryName = getIntent().getStringExtra(EXTRA_LIST_CATEGORY_NAME);

        if (savedInstanceState == null){
            addFragment(0);
        }

        mProductViewModel = new ViewModelProvider(this).get(ProductViewModel.class);

        LiveData<Boolean> openedLiveData = mProductViewModel.getOpenLiveData();

    }

    public static void start(Context context, String categoryName) {
        Intent start = new Intent(context, ProductActivity.class);
        start.putExtra(EXTRA_LIST_CATEGORY_NAME, categoryName);
        start.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(start);
    }

    private void addFragment(int state) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container,
                        ProductListFragment.newInstance(mCategoryName, state))
//                .replace(R.id.fragment_container, CategoryListFragment.newInstance())
                .commit();
    }

}