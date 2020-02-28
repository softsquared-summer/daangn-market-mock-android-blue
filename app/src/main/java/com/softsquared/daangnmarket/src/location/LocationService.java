package com.softsquared.daangnmarket.src.location;

import com.softsquared.daangnmarket.src.location.interfaces.LocationActivityView;
import com.softsquared.daangnmarket.src.location.interfaces.LocationRecyclerViewAdapterView;
import com.softsquared.daangnmarket.src.location.interfaces.LocationRetrofitInterface;
import com.softsquared.daangnmarket.src.location.models.ResponseAddress;
import com.softsquared.daangnmarket.src.location.models.ResponseLocationReset;
import com.softsquared.daangnmarket.src.main.interfaces.MainRetrofitInterface;
import com.softsquared.daangnmarket.src.main.models.DefaultResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.softsquared.daangnmarket.src.ApplicationClass.getRetrofit;

public class LocationService {
    private final LocationActivityView mLocationActivityView;

    public LocationService(LocationActivityView mLocationActivityView) {
        this.mLocationActivityView = mLocationActivityView;
    }



    void getSearchAddress(String address) {
        final LocationRetrofitInterface locationRetrofitInterface = getRetrofit().create(LocationRetrofitInterface.class);
        locationRetrofitInterface.getSearchAddress(address).enqueue(new Callback<ResponseAddress>() {
            @Override
            public void onResponse(Call<ResponseAddress> call, Response<ResponseAddress> response) {
                final ResponseAddress responseAddress = response.body();
                if (responseAddress.getIsSuccess())
                    mLocationActivityView.validateLocationSuccess(responseAddress.getResult(), responseAddress.getIsSuccess(), responseAddress.getCode(), responseAddress.getMessage());
            }

            @Override
            public void onFailure(Call<ResponseAddress> call, Throwable t) {
                mLocationActivityView.validateLocationFailure();
            }
        });
    }
}
