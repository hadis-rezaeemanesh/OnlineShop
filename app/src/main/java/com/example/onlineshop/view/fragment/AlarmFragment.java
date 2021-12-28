package com.example.onlineshop.view.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.onlineshop.R;
import com.example.onlineshop.databinding.FragmentAlarmBinding;
import com.example.onlineshop.viewModel.AlarmViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class AlarmFragment extends BottomSheetDialogFragment {

    private FragmentAlarmBinding mBinding;
    private AlarmViewModel mViewModel;

    public AlarmFragment() {
        // Required empty public constructor
    }

    public static AlarmFragment newInstance() {
        AlarmFragment fragment = new AlarmFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewModel = new ViewModelProvider(this).get(AlarmViewModel.class);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_alarm,
                container,
                false);

        mBinding.setAlarmViewModel(mViewModel);

        return mBinding.getRoot();
    }
}