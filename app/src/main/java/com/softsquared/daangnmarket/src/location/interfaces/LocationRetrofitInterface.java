package com.softsquared.daangnmarket.src.location.interfaces;

import com.softsquared.daangnmarket.src.location.models.RequestLocationReset;
import com.softsquared.daangnmarket.src.location.models.ResponseAddress;
import com.softsquared.daangnmarket.src.location.models.ResponseLocationReset;
import com.softsquared.daangnmarket.src.main.models.DefaultResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.Query;

public interface LocationRetrofitInterface {
    @GET("/location")
    Call<ResponseAddress> getSearchAddress(
            @Query("address") final String address
    );

    @PATCH("/location")
    Call<ResponseLocationReset> patchLocationReset(
            @Body RequestLocationReset params
    );
}
