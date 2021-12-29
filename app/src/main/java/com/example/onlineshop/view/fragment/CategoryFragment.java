package com.example.onlineshop.view.fragment;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.onlineshop.observers.ObserverEvent;
import com.example.onlineshop.R;
import com.example.onlineshop.adapter.CategoryAdapter;
import com.example.onlineshop.databinding.FragmentCategoryBinding;
import com.example.onlineshop.model.Category;
import com.example.onlineshop.viewModel.CategoryViewModel;
import java.util.List;

public class CategoryFragment extends VisibleFragment {

    private FragmentCategoryBinding mBinding;
    private CategoryViewModel mCategoryViewModel;
    private int page = 1;

    public CategoryFragment() {
        // Required empty public constructor
    }

    public static CategoryFragment newInstance() {
        CategoryFragment fragment = new CategoryFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mCategoryViewModel = new ViewModelProvider(this).get(CategoryViewModel.class);
        mCategoryViewModel.fetchListCategory(page);
        mCategoryViewModel.getListCategoryLiveData().observe(
                this, new Observer<List<Category>>() {
            @Override
            public void onChanged(List<Category> categories) {
                setAdapters(categories);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_category,
                container,
                false );
        initViews();
        listeners();
        navListener();
        return mBinding.getRoot();
    }

    private void listeners() {
        mBinding.recyclerViewCategoryList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (recyclerView.canScrollHorizontally(1) &&
                        newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if (page <= mCategoryViewModel.getPageCount().getValue())
                        mCategoryViewModel.fetchListCategory(page);
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                dx = recyclerView.FOCUS_LEFT;

                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    private void initViews() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(
                getActivity(),
                LinearLayoutManager.HORIZONTAL,
                true);
        layoutManager.setReverseLayout(true);
        mBinding.recyclerViewCategoryList.setLayoutManager(layoutManager);
//        mBinding.listTitle.setText(title);
    }

    private void setAdapters(List<Category> items) {
        CategoryAdapter adapter = new CategoryAdapter(mCategoryViewModel);
        mBinding.recyclerViewCategoryList.setAdapter(adapter);
    }

    private void navListener() {

        LiveData<String> navigateLiveData = mCategoryViewModel.getNavigateLiveData();
        navigateLiveData.observe(getViewLifecycleOwner(),
                new ObserverEvent<String>(this, navigateLiveData) {
                    @Override
                    public void onChanged(String s) {
                        super.onChanged(s);
                        if (s != null){
                            NavOptions navOptions = new NavOptions.Builder().setPopUpTo(
                                    R.id.productListFragment, true).build();
                            Navigation.findNavController(mBinding.getRoot()).navigate(
                                    CategoryFragmentDirections.actionCategoryFragmentToProductListFragment(s), navOptions);
                        }
                    }
                });
    }
}