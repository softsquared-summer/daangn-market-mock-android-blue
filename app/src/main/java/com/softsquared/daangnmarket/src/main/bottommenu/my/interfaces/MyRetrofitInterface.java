package com.softsquared.daangnmarket.src.main.bottommenu.my.interfaces;

import com.softsquared.daangnmarket.src.main.bottommenu.my.models.ResponseProfile;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MyRetrofitInterface {
    @GET("/user/{userNo}")
    Call<ResponseProfile> getProfile(
            @Path("userNo") final int userNo
    );
}
