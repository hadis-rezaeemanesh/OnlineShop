package com.example.onlineshop.view.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.onlineshop.observers.ObserverEvent;
import com.example.onlineshop.R;
import com.example.onlineshop.databinding.FragmentSortListBinding;
import com.example.onlineshop.viewModel.SortListViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class SortListFragment extends BottomSheetDialogFragment {

    private FragmentSortListBinding mBinding;
    private SortListViewModel mViewModel;
    public SortListFragment() {
        // Required empty public constructor
    }

    public static SortListFragment newInstance() {
        SortListFragment fragment = new SortListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewModel = new ViewModelProvider(this).get(SortListViewModel.class);
        mViewModel.fetchTotalProductsForCategory();
        LiveData<Boolean> mDismissDialogLiveData = mViewModel.getDismissDialog();
        mDismissDialogLiveData.observe(this, new ObserverEvent<Boolean>(
                this,
                mDismissDialogLiveData) {
            @Override
            public void onChanged(Boolean aBoolean) {
                super.onChanged(aBoolean);
                if (aBoolean)
                    dismiss();
            }
        });
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_product_list, container, false);
        mBinding.setSortListViewModel(mViewModel);
        return mBinding.getRoot();
    }
}