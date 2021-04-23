package com.example.onlineshop.fragment;

import android.os.Bundle;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.onlineshop.R;
import com.example.onlineshop.activity.MainActivity;
import com.google.android.material.button.MaterialButton;

public class ToolbarFragment extends Fragment {

    private MaterialButton mButtonSearch;
    private MaterialButton mButtonBuy;
    private MaterialButton mButtonMenu;
    private DrawerLayout drawerLayout;

    public ToolbarFragment() {
        // Required empty public constructor
    }
    // TODO: Rename and change types and number of parameters
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
        View view = inflater.inflate(R.layout.activity_online_shop, container, false);

        findViews(view);

        mButtonMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getActivity() instanceof MainActivity){
                    drawerLayout.openDrawer(Gravity.RIGHT);
                }
            }
        });
        return view;
    }

    private void findViews(View view) {
        mButtonSearch = view.findViewById(R.id.search_btn);
        mButtonBuy = view.findViewById(R.id.buy_shopping_btn);
        mButtonMenu = view.findViewById(R.id.menu_btn);
        drawerLayout = view.findViewById(R.id.drawer_layout_online_shop);
    }
}