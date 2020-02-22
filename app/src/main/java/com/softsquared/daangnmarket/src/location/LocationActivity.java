package com.softsquared.daangnmarket.src.location;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.softsquared.daangnmarket.R;
import com.softsquared.daangnmarket.src.BaseActivity;
import com.softsquared.daangnmarket.src.location.interfaces.LocationActivityView;
import com.softsquared.daangnmarket.src.location.models.ResponseAddress;

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
    }

    public void getAddress() {
        LocationService locationService = new LocationService(this);
        locationService.getSearchAddress(mSearchEditText.getText().toString());
    }

    @Override
    public void validateLocationSuccess(ArrayList<ResponseAddress.Result> result, boolean isSuccess, int code, String message) {
        System.out.println(isSuccess + " " + code + " " + message);
        LocationRecyclerViewAdapter locationRecyclerViewAdapter = new LocationRecyclerViewAdapter(result);
        mAddressList.setAdapter(locationRecyclerViewAdapter);
    }

    @Override
    public void validateLocationFailure() {
        showCustomToast(getString(R.string.network_error));
    }

    public void customOnClick(View view) {
        switch (view.getId()) {
            case R.id.location_btn_cur:
                getAddress();
        }
    }
}
