package com.softsquared.daangnmarket.src.location;

import com.softsquared.daangnmarket.src.location.interfaces.LocationRecyclerViewAdapterView;
import com.softsquared.daangnmarket.src.location.interfaces.LocationRetrofitInterface;
import com.softsquared.daangnmarket.src.location.models.RequestLocationReset;
import com.softsquared.daangnmarket.src.location.models.ResponseLocationReset;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.softsquared.daangnmarket.src.ApplicationClass.getRetrofit;

public class LocationRecyclerViewService {
    private final LocationRecyclerViewAdapterView mLocationRecyclerViewAdapterView;

    public LocationRecyclerViewService(LocationRecyclerViewAdapterView locationRecyclerViewAdapterView) {
        this.mLocationRecyclerViewAdapterView = locationRecyclerViewAdapterView;
    }

    void patchLocationReset(RequestLocationReset requestLocationReset) {
        final LocationRetrofitInterface locationRetrofitInterface = getRetrofit().create(LocationRetrofitInterface.class);
        locationRetrofitInterface.patchLocationReset(requestLocationReset).enqueue(new Callback<ResponseLocationReset>() {
            @Override
            public void onResponse(Call<ResponseLocationReset> call, Response<ResponseLocationReset> response) {
                final ResponseLocationReset responseLocationReset = response.body();
                mLocationRecyclerViewAdapterView.validateLocationResetSuccess(responseLocationReset.getIsSuccess(), responseLocationReset.getCode(), responseLocationReset.getMessage());
            }

            @Override
            public void onFailure(Call<ResponseLocationReset> call, Throwable t) {
                mLocationRecyclerViewAdapterView.validateLocationResetFailure();
            }
        });
    }
}
