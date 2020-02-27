package com.softsquared.daangnmarket.src.main.bottommenu.home.interfaces;

import com.softsquared.daangnmarket.src.location.models.ResponseAddress;
import com.softsquared.daangnmarket.src.main.bottommenu.home.models.ResponseProduct;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface HomeRetrofitInterface {
    @GET("/product")
    Call<ResponseProduct> getProduct(
            @Query("address") final String address,
            @Query("page") final int page
    );
}
