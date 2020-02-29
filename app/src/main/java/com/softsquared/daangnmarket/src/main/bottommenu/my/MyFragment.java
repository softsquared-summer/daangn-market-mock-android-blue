package com.softsquared.daangnmarket.src.main.bottommenu.my;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.softsquared.daangnmarket.R;
import com.softsquared.daangnmarket.src.appSetting.AppSettingActivity;
import com.softsquared.daangnmarket.src.login.LoginActivity;
import com.softsquared.daangnmarket.src.main.MainActivity;
import com.softsquared.daangnmarket.src.main.bottommenu.my.interfaces.MyFragmentView;
import com.softsquared.daangnmarket.src.main.bottommenu.my.models.ResponseProfile;
import com.softsquared.daangnmarket.src.profile.ProfileActivity;

import java.util.ArrayList;

import static com.softsquared.daangnmarket.src.ApplicationClass.X_ACCESS_TOKEN;

public class MyFragment extends Fragment implements MyFragmentView {

    Toolbar mToolbar;
    LinearLayout mAppSettingLayout, mProfileLayout;
    TextView mProfileID, mProfileAddress;
    ImageView mProfileImage;
    String mJwt = null, mURL, mID;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my, container, false);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(X_ACCESS_TOKEN, Context.MODE_PRIVATE);
        mJwt = sharedPreferences.getString(X_ACCESS_TOKEN, null);

        mToolbar = view.findViewById(R.id.tb_my);
        ((AppCompatActivity)getActivity()).setSupportActionBar(mToolbar);
        mToolbar.setTitle(getString(R.string.actionbar_title_my));
        setHasOptionsMenu(true);
        // Inflate the layout for this fragment

        mAppSettingLayout = view.findViewById(R.id.my_linear_layout_app_setting);
        mProfileLayout = view.findViewById(R.id.my_profile);
        mProfileID = view.findViewById(R.id.my_tv_id);
        mProfileAddress = view.findViewById(R.id.my_tv_address);
        mProfileImage = view.findViewById(R.id.my_iv_profile);

        Glide.with(getContext()).load(getString(R.string.default_profile_url)).into(mProfileImage);
        mProfileImage.setBackgroundResource(R.drawable.round_image_view);
        mProfileImage.setClipToOutline(true);

        if (mJwt == null) {
            mProfileID.setText(getString(R.string.my_login));
            mProfileID.setTextColor(ContextCompat.getColor(getContext(), R.color.start_button_color));
            SharedPreferences sharedPreferences1 = getActivity().getSharedPreferences("address", Context.MODE_PRIVATE);
            String address = sharedPreferences1.getString("address", null);
            mProfileAddress.setText(address);
        }
        else {
            getProfile();
        }

        mAppSettingLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickAppSetting();
            }
        });

        mProfileLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mJwt == null) {
                    Intent intent = new Intent(getContext(), LoginActivity.class);
                    startActivity(intent);
                }
                else {
                    Intent intent = new Intent(getContext(), ProfileActivity.class);
                    intent.putExtra("id", mID);
                    intent.putExtra("url", mURL);
                    startActivity(intent);
                }
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

    @Override
    public void validateProfileSuccess(boolean isSuccess, int code, String message, ArrayList<ResponseProfile.Result> resultArrayList) {
        if (isSuccess) {
            Glide.with(getContext()).load(resultArrayList.get(0).getProfileUrl()).into(mProfileImage);
            mProfileImage.setBackgroundResource(R.drawable.profile_image_view);
            mProfileImage.setClipToOutline(true);
            mProfileID.setText(resultArrayList.get(0).getId());
            mProfileAddress.setText(resultArrayList.get(0).getAddress());
            mURL = resultArrayList.get(0).getProfileUrl();
            mID = resultArrayList.get(0).getId();
        }
        else {
            Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void validateProfileFailure() {
        Toast.makeText(getContext(), getString(R.string.network_error), Toast.LENGTH_LONG).show();
    }

    public void getProfile() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("userNo", Context.MODE_PRIVATE);
        int userNo = sharedPreferences.getInt("userNo", 0);
        MyService myService = new MyService(this);
        myService.getProfile(userNo);
    }
}
