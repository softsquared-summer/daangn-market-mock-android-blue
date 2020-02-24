package com.softsquared.daangnmarket.src.product.interfaces;

import com.softsquared.daangnmarket.src.main.models.DefaultResponse;
import com.softsquared.daangnmarket.src.product.models.ResponseProduct;
import com.softsquared.daangnmarket.src.product.models.ResponseProductImage;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ProductRetrofitInterface {
    @GET("/product/{productNo}")
    Call<ResponseProduct> getProduct(
            @Path("productNo") int productNo
    );

    @GET("/product/{productNo}/image")
    Call<ResponseProductImage> getProductImage(
            @Path("productNo") int productNo
    );
}
