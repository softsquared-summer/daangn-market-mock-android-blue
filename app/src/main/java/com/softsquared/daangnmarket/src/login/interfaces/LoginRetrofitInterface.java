package com.softsquared.daangnmarket.src.login.interfaces;

import com.softsquared.daangnmarket.src.login.models.LoginResponse;
import com.softsquared.daangnmarket.src.login.models.RequestMessage;
import com.softsquared.daangnmarket.src.main.models.DefaultResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginRetrofitInterface {
    @POST("/message")
    Call<LoginResponse> postPhone(@Body RequestMessage params);
}
