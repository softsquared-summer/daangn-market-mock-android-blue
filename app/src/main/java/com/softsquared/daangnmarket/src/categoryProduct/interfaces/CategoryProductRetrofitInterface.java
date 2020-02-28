package com.softsquared.daangnmarket.src.categoryProduct.interfaces;

import com.softsquared.daangnmarket.src.anotherAll.models.ResponseProductAnother;
import com.softsquared.daangnmarket.src.categoryProduct.models.ResponseCategoryProduct;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CategoryProductRetrofitInterface {
    @GET("/product")
    Call<ResponseCategoryProduct> getCategoryProduct(
            @Query("categoriesNo") int categoriesNo,
            @Query("address") String address,
            @Query("page") int page
    );
}
