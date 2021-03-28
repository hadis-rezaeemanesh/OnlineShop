package com.example.onlineshop.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.onlineshop.R;
import com.example.onlineshop.adapter.ProductAdapter;
import com.example.onlineshop.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductListFragment extends Fragment {
    private RecyclerView mRecyclerView;

    public ProductListFragment() {
        // Required empty public constructor
    }
    // TODO: Rename and change types and number of parameters
    public static ProductListFragment newInstance() {
        ProductListFragment fragment = new ProductListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_product_list, container, false);
        findViews(view);
        initViews();
        setAdapters();
        return view;
    }

    private void setAdapters() {
        List<Product> items = new ArrayList<>();
        Product product = new Product();
        product.setName("Phone");
        product.setPrice("5000000 toman");
        for (int i = 0; i <100 ; i++) {
            items.add(product);
        }
        ProductAdapter productAdapter = new ProductAdapter(items);
        mRecyclerView.setAdapter(productAdapter);
    }

    private void initViews() {
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
    }

    private void findViews(View view) {
        mRecyclerView = view.findViewById(R.id.recycler_view_products);
    }
}