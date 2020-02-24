package com.softsquared.daangnmarket.src.main.bottommenu.my;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.appcompat.widget.Toolbar;

import com.softsquared.daangnmarket.R;
import com.softsquared.daangnmarket.src.appSetting.AppSettingActivity;

public class MyFragment extends Fragment {

    Toolbar mToolbar;
    LinearLayout mAppSettingLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my, container, false);
        mToolbar = view.findViewById(R.id.tb_my);
        ((AppCompatActivity)getActivity()).setSupportActionBar(mToolbar);
        mToolbar.setTitle(getString(R.string.actionbar_title_my));
        setHasOptionsMenu(true);
        // Inflate the layout for this fragment

        mAppSettingLayout = view.findViewById(R.id.my_linear_layout_app_setting);
        mAppSettingLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickAppSetting();
            }
        });

        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_actionbar_my, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    public void clickAppSetting() {
        Intent intent = new Intent(getActivity(), AppSettingActivity.class);
        startActivity(intent);
    }

    public void customOnClick (View view) {
        switch(view.getId()) {
            case R.id.my_linear_layout_app_setting:
                System.out.println("여기");
                clickAppSetting();
                break;
        }
    }
}
