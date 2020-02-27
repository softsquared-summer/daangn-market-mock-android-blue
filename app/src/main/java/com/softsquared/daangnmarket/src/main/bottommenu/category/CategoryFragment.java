package com.softsquared.daangnmarket.src.main.bottommenu.category;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.softsquared.daangnmarket.R;
import com.softsquared.daangnmarket.src.main.bottommenu.category.interfaces.CategoryFragmentView;
import com.softsquared.daangnmarket.src.main.bottommenu.category.models.ResponseCategory;

import java.util.ArrayList;
import java.util.Locale;

public class CategoryFragment extends Fragment implements CategoryFragmentView {

    Toolbar mToolbar;
    RecyclerView mRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        mToolbar = view.findViewById(R.id.tb_category);
        ((AppCompatActivity)getActivity()).setSupportActionBar(mToolbar);
        mToolbar.setTitle(getString(R.string.actionbar_title_category));
        setHasOptionsMenu(true);

        mRecyclerView = view.findViewById(R.id.category_rv);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        getCategory();

        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_actionbar_category, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void validateCategorySuccess(boolean isSuccess, int code, String message, ArrayList<ResponseCategory.Result> result) {
        if (isSuccess) {
            CategoryRecyclerViewAdapter categoryRecyclerViewAdapter = new CategoryRecyclerViewAdapter(result);
            mRecyclerView.setAdapter(categoryRecyclerViewAdapter);
        }
    }

    @Override
    public void validateCategoryFailure() {
        Toast.makeText(getActivity().getApplicationContext(), getString(R.string.network_error), Toast.LENGTH_LONG).show();
    }

    void getCategory() {
        CategoryService categoryService = new CategoryService(this);
        categoryService.getCategory();
    }
}
