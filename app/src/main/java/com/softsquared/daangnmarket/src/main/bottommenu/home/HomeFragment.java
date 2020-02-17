package com.softsquared.daangnmarket.src.main.bottommenu.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.softsquared.daangnmarket.R;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    Toolbar mToolbar;
    ArrayList<ProductItem> mProductItems = new ArrayList<>();
    RecyclerView mRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        mToolbar = view.findViewById(R.id.tb_home);
        ((AppCompatActivity)getActivity()).setSupportActionBar(mToolbar);
        mToolbar.setTitle(getString(R.string.actionbar_title_home));
        setHasOptionsMenu(true);

        for (int i = 0; i < 20; i++) {
            ProductItem productItem = new ProductItem();
            productItem.setProductImage(R.drawable.menu_category);
            productItem.setProductName("창국이");
            mProductItems.add(productItem);
        }

        mRecyclerView = view.findViewById(R.id.home_rv_product);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        ProductRecyclerViewAdapter productRecyclerViewAdapter = new ProductRecyclerViewAdapter(mProductItems);
        mRecyclerView.setAdapter(productRecyclerViewAdapter);

        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_actionbar_home, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
}
