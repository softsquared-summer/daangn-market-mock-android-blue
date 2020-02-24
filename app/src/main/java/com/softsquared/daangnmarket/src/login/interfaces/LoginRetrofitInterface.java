package com.softsquared.daangnmarket.src.login.interfaces;

import com.softsquared.daangnmarket.src.login.models.LoginResponse;
import com.softsquared.daangnmarket.src.login.models.MessageResponse;
import com.softsquared.daangnmarket.src.login.models.RequestLogin;
import com.softsquared.daangnmarket.src.login.models.RequestMessage;
import com.softsquared.daangnmarket.src.login.models.RequestPhoneCert;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginRetrofitInterface {
    @POST("/message")
    Call<MessageResponse> postPhone(@Body RequestMessage params);

    @POST("/phonecert")
    Call<MessageResponse> postCert(@Body RequestPhoneCert params);

    @POST("/jwt")
    Call<LoginResponse> postLogin(@Body RequestLogin params);
}
