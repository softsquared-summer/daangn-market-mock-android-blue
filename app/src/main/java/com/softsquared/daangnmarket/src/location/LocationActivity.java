package com.softsquared.daangnmarket.src.location;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.softsquared.daangnmarket.R;
import com.softsquared.daangnmarket.src.BaseActivity;
import com.softsquared.daangnmarket.src.location.interfaces.LocationActivityView;
import com.softsquared.daangnmarket.src.location.models.ResponseAddress;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class LocationActivity extends BaseActivity implements LocationActivityView {

    EditText mSearchEditText;
    Button mCurrentAddressButton;
    TextView mTextView;
    RecyclerView mAddressList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        mSearchEditText = findViewById(R.id.location_et_search);
        mCurrentAddressButton = findViewById(R.id.location_btn_cur);
        mTextView = findViewById(R.id.location_text_view);
        mAddressList = findViewById(R.id.location_rv_address_list);
        mAddressList.setLayoutManager(new LinearLayoutManager(this));

        getAddress("");

        mSearchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() > 0)
                    getAddress(s.toString());
                else {
                    ArrayList<ResponseAddress.Result> tempList = new ArrayList<>();
                    LocationRecyclerViewAdapter locationRecyclerViewAdapter = new LocationRecyclerViewAdapter(tempList, LocationActivity.this);
                    mAddressList.setAdapter(locationRecyclerViewAdapter);
                }
            }
        });
    }

    public void getAddress(String etStr) {
        LocationService locationService = new LocationService(this);
        locationService.getSearchAddress(etStr);
    }

    @Override
    public void validateLocationSuccess(ArrayList<ResponseAddress.Result> result, boolean isSuccess, int code, String message) {
        LocationRecyclerViewAdapter locationRecyclerViewAdapter = new LocationRecyclerViewAdapter(result, this);
        mAddressList.setAdapter(locationRecyclerViewAdapter);
    }

    @Override
    public void validateLocationFailure() {
        showCustomToast(getString(R.string.network_error));
    }

    public void customOnClick(View view) {
        switch (view.getId()) {
            case R.id.location_btn_cur:
        }
    }
}
