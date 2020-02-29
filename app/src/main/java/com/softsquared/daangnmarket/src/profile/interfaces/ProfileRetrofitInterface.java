package com.softsquared.daangnmarket.src.profile.interfaces;

import com.softsquared.daangnmarket.src.main.bottommenu.my.models.ResponseProfile;
import com.softsquared.daangnmarket.src.profile.models.RequestChangeProfile;
import com.softsquared.daangnmarket.src.profile.models.ResponseChangeProfile;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.Path;

public interface ProfileRetrofitInterface {
    @PATCH("/user")
    Call<ResponseChangeProfile> patchProfile(@Body RequestChangeProfile params);
}
