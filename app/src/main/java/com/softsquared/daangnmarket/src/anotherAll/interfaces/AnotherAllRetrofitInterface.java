package com.softsquared.daangnmarket.src.anotherAll.interfaces;



import com.softsquared.daangnmarket.src.anotherAll.models.ResponseProductAnother;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface AnotherAllRetrofitInterface {
    @GET("/user/{userNo}/product")
    Call<ResponseProductAnother> getAnotherProductAll(
            @Path("userNo") int userNo,
            @Query("page") int page
    );

    @GET("/user/{userNo}/product")
    Call<ResponseProductAnother> getAnotherProductStatus(
            @Path("userNo") int userNo,
            @Query("status") char status,
            @Query("page") int page
    );
}
