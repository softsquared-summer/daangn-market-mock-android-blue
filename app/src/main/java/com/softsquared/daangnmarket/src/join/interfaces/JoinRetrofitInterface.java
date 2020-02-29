package com.softsquared.daangnmarket.src.join.interfaces;

import com.softsquared.daangnmarket.src.join.models.JoinResponse;
import com.softsquared.daangnmarket.src.join.models.RequestJoin;
import com.softsquared.daangnmarket.src.join.models.RequestLogin;
import com.softsquared.daangnmarket.src.join.models.ResponseLogin;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.PATCH;
import retrofit2.http.POST;

public interface JoinRetrofitInterface {
    @POST("/user")
    Call<JoinResponse> postJoin(@Body RequestJoin params);

    @POST("/jwt")
    Call<ResponseLogin> postLogin(@Body RequestLogin params);
}
