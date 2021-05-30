package com.example.onlineshop.view.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.onlineshop.R;
import com.example.onlineshop.databinding.ActivityOnlineShopBinding;
import com.example.onlineshop.databinding.FragmentToolbarBinding;
import com.example.onlineshop.view.activity.HomePageActivity;

public class ToolbarFragment extends Fragment {

    private FragmentToolbarBinding mBinding;

    public ToolbarFragment() {
        // Required empty public constructor
    }

    public static ToolbarFragment newInstance() {
        ToolbarFragment fragment = new ToolbarFragment();
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

        mBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_toolbar, container, false);



      /*  setListeners();*/
        return mBinding.getRoot();
    }

 /*   private void setListeners() {
        mBinding.menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getActivity()instanceof HomePageActivity){
                    ((HomePageActivity) getActivity()).openNavigationView();
                }
            }
        });
    }
*/
}