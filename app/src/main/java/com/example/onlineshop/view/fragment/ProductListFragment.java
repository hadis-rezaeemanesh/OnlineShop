package com.example.onlineshop.view.fragment;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.onlineshop.ObserverEvent;
import com.example.onlineshop.R;
import com.example.onlineshop.adapter.ProductAdapter;
import com.example.onlineshop.databinding.FragmentProductListBinding;
import com.example.onlineshop.model.Product;
import com.example.onlineshop.viewModel.ProductListViewModel;

import java.util.List;

public class ProductListFragment extends Fragment {

    public static final String ARGS_LIST_NAME = "listName";
    public static final String ARGS_LIST_STATE = "listState";

    private String nameList;
    private int stateList;

    private FragmentProductListBinding mBinding;
    private ProductListViewModel mProductViewModel;
    private int page = 1;

    public ProductListFragment() {
        // Required empty public constructor
    }
    public static ProductListFragment newInstance() {
        ProductListFragment fragment = new ProductListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        mProductViewModel = new ViewModelProvider(this).get(ProductListViewModel.class);
        mProductViewModel.getListProductLiveData().observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                setAdapters(products);
            }
        });

        NavController navController = NavHostFragment.findNavController(this);
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                navController.popBackStack(
                        R.id.action_categoryFragment_to_productListFragment,
                        true);
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_product_list, container, false);
        mBinding.setProductListViewModel(mProductViewModel);

        initViews();
        listeners();
        openDrawer();
        navListener();
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() != null) {
            String categoryName = getArguments().getString("categoryName");
            mBinding.listProductsTxtView.setText(categoryName);
        }
    }

    private void setAdapters(List<Product> items) {
        ProductAdapter productAdapter = new ProductAdapter(this,mProductViewModel, items, 0);
        mBinding.recyclerViewProducts.setAdapter(productAdapter);
    }

    private void initViews() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                getActivity(),
                LinearLayoutManager.HORIZONTAL,
                false);
        linearLayoutManager.setReverseLayout(true);
        mBinding.recyclerViewProducts.setLayoutManager(linearLayoutManager);
    }

    private void listeners(){
        mBinding.recyclerViewProducts.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (newState == 1) {
                    if (page <= mProductViewModel.getPageCount().getValue() &&
                            mProductViewModel.getListProductLiveData().getValue().size() == 10)

                        mProductViewModel.fetchProductsAsync(++page);
                }
            }
        });
    }

    private void openDrawer(){
        LiveData<Boolean> openLiveData = mProductViewModel.getOpenLiveData();
        openLiveData.observe(getActivity(), new ObserverEvent<Boolean>(this, openLiveData) {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean){
                    if (!mBinding.drawerLayoutProductList.isDrawerOpen(GravityCompat.START))
                        mBinding.drawerLayoutProductList.openDrawer(GravityCompat.START);
                }
            }
        });
    }

    private void navListener() {
        LiveData<Boolean> showDialogLiveData = mProductViewModel.getSortDialogStart();
        showDialogLiveData.observe(
                getViewLifecycleOwner(),
                new ObserverEvent<Boolean>(this, showDialogLiveData) {
            @Override
            public void onChanged(Boolean aBoolean) {
                super.onChanged(aBoolean);
                if (aBoolean) {
                    Navigation.findNavController(mBinding.getRoot()).navigate(R.id.sortListFragment);
                }
            }
        });

        mProductViewModel.getClickedItem().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer id) {
                Log.e("productItemClicked", "this id clicked in pvm change LiveData");
                Bundle bundle = new Bundle();
                bundle.putInt("productId", id);
                Navigation.findNavController(mBinding.getRoot()).navigate(R.id.productPageFragment, bundle);
            }
        });


    }

}