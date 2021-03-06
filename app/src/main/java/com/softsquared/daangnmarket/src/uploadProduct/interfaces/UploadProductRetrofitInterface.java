package com.softsquared.daangnmarket.src.uploadProduct.interfaces;

import com.softsquared.daangnmarket.src.uploadProduct.models.RequestUploadProduct;
import com.softsquared.daangnmarket.src.uploadProduct.models.RequestUploadProductImage;
import com.softsquared.daangnmarket.src.uploadProduct.models.ResponseUploadProduct;
import com.softsquared.daangnmarket.src.uploadProduct.models.ResponseUploadProductCategory;
import com.softsquared.daangnmarket.src.uploadProduct.models.ResponseUploadProductImage;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface UploadProductRetrofitInterface {
    @POST("/product")
    Call<ResponseUploadProduct> postUploadProduct(@Body RequestUploadProduct params);

    @POST("/product/image")
    Call<ResponseUploadProductImage> postUploadProductImage(@Body RequestUploadProductImage params);

    @GET("/categories")
    Call<ResponseUploadProductCategory> getUploadProductCategory();
}
