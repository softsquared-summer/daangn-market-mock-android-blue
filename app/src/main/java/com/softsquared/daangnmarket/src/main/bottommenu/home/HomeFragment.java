package com.softsquared.daangnmarket.src.main.bottommenu.home;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.softsquared.daangnmarket.R;
import com.softsquared.daangnmarket.src.location.LocationActivity;
import com.softsquared.daangnmarket.src.location.models.ResponseAddress;
import com.softsquared.daangnmarket.src.login.LoginActivity;
import com.softsquared.daangnmarket.src.main.MainActivity;
import com.softsquared.daangnmarket.src.main.bottommenu.home.interfaces.HomeFragmentView;
import com.softsquared.daangnmarket.src.main.bottommenu.home.models.ResponseProduct;

import java.util.ArrayList;

import static android.content.Context.WINDOW_SERVICE;

public class HomeFragment extends Fragment implements HomeFragmentView {

    Toolbar mToolbar;
    RecyclerView mRecyclerView;
    HomeCustomDialog mHomeCustomDialog;
    ResponseAddress.Result mAddressResult;
    TextView mToolbarTitle;
    ArrayList<ResponseProduct.Result> mList = new ArrayList<>();
    int mPageNo = 0;
    public ProgressDialog mProgressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("address", Context.MODE_PRIVATE);
        String address = sharedPreferences.getString("address", null);
        String[] addressStrArr = address.split("\\s");

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        mToolbar = view.findViewById(R.id.tb_home);
        mToolbarTitle = view.findViewById(R.id.home_fragment_tv_toolbar_title);
        mToolbarTitle.setText(addressStrArr[addressStrArr.length - 1]);
        mToolbarTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LocationActivity.class);
                startActivity(intent);
            }
        });
        ((AppCompatActivity)getActivity()).setSupportActionBar(mToolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        setHasOptionsMenu(true);



        Intent intent = getActivity().getIntent();
        mAddressResult = (ResponseAddress.Result)intent.getSerializableExtra("address");

        if (intent.getSerializableExtra("address") != null) {
            intent.removeExtra("address");
            String tempAddress = mAddressResult.getAddress();
            String[] strArr = tempAddress.split("\\s");
            mHomeCustomDialog = new HomeCustomDialog(getContext(),positiveListener,negativeListener, strArr[strArr.length - 1] , getString(R.string.popup_string1), getString(R.string.popup_string2));
            mHomeCustomDialog.show();
        }

        mRecyclerView = view.findViewById(R.id.home_rv_product);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        showProgressDialog();
        getProduct(mPageNo);

        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_actionbar_home, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    private View.OnClickListener positiveListener = new View.OnClickListener() {
        public void onClick(View v) {
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            intent.putExtra("address", mAddressResult);
            startActivity(intent);
            getActivity().finish();
            mHomeCustomDialog.dismiss();
        }
    };

    private View.OnClickListener negativeListener = new View.OnClickListener() {
        public void onClick(View v) {
            mHomeCustomDialog.dismiss();
        }
    };

    public void getProduct(int pageNo) {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("address", Context.MODE_PRIVATE);
        String address = sharedPreferences.getString("address", null);
        HomeService homeService = new HomeService(this);
        homeService.getProduct(address, pageNo);
    }

    @Override
    public void validateProductSuccess(boolean isSuccess, int code, String message, ArrayList<ResponseProduct.Result> resultArrayList) {
        if (resultArrayList.size() > 0) {
            for (int i = 0; i < resultArrayList.size(); i++) {
                mList.add(resultArrayList.get(i));
            }
            mPageNo++;
            getProduct(mPageNo);
        }
        else {
            ProductRecyclerViewAdapter productRecyclerViewAdapter = new ProductRecyclerViewAdapter(mList);
            mRecyclerView.setAdapter(productRecyclerViewAdapter);
            hideProgressDialog();
        }
    }

    @Override
    public void validateProductFailure() {
        Toast.makeText(getActivity().getApplicationContext(), getString(R.string.network_error), Toast.LENGTH_LONG).show();
    }

    public void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(getContext());
            mProgressDialog.setMessage(getString(R.string.loading));
            mProgressDialog.setIndeterminate(true);
            mProgressDialog.setCanceledOnTouchOutside(false);
        }

        mProgressDialog.show();
    }

    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }
}
