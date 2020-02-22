package com.softsquared.daangnmarket.src.location.interfaces;

import com.softsquared.daangnmarket.src.location.models.ResponseAddress;
import com.softsquared.daangnmarket.src.main.models.DefaultResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LocationRetrofitInterface {
    @GET("/location")
    Call<ResponseAddress> getSearchAddress(
            @Query("address") final String address
    );
}
