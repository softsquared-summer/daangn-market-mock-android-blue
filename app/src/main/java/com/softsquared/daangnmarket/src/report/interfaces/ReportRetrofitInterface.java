package com.softsquared.daangnmarket.src.report.interfaces;

import com.softsquared.daangnmarket.src.main.models.DefaultResponse;
import com.softsquared.daangnmarket.src.report.models.RequestProductReport;
import com.softsquared.daangnmarket.src.report.models.RequestUserReport;
import com.softsquared.daangnmarket.src.report.models.ResponseReport;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ReportRetrofitInterface {
    @POST("/report")
    Call<ResponseReport> postReportProduct(@Body RequestProductReport params);

    @POST("/report")
    Call<ResponseReport> postReportUser(@Body RequestUserReport params);
}
