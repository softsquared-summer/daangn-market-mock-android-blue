package com.softsquared.daangnmarket.src.main.bottommenu.home.interfaces;

import com.softsquared.daangnmarket.src.location.models.ResponseAddress;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface HomeRetrofitInterface {
    @GET("/product")
    Call<ResponseAddress> getProduct(
            @Query("page") final String address
    );
}
