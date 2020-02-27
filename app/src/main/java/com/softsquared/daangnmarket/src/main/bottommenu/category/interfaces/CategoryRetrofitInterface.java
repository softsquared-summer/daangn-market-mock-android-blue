package com.softsquared.daangnmarket.src.main.bottommenu.category.interfaces;

import com.softsquared.daangnmarket.src.main.bottommenu.category.models.ResponseCategory;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CategoryRetrofitInterface {
    @GET("/categories")
    Call<ResponseCategory> getCategory();
}
