package com.softsquared.daangnmarket.src.report;

import com.softsquared.daangnmarket.src.product.models.ResponseProductAnother;
import com.softsquared.daangnmarket.src.report.interfaces.ReportActivityView;
import com.softsquared.daangnmarket.src.report.interfaces.ReportRetrofitInterface;
import com.softsquared.daangnmarket.src.report.models.RequestProductReport;
import com.softsquared.daangnmarket.src.report.models.RequestUserReport;
import com.softsquared.daangnmarket.src.report.models.ResponseReport;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.softsquared.daangnmarket.src.ApplicationClass.getRetrofit;

public class ReportService {
    private ReportActivityView mReportActivityView;

    public ReportService(ReportActivityView reportActivityView) {
        mReportActivityView = reportActivityView;
    }

    void postReportProduct(RequestProductReport requestProductReport) {
        final ReportRetrofitInterface reportRetrofitInterface = getRetrofit().create(ReportRetrofitInterface.class);
        reportRetrofitInterface.postReportProduct(requestProductReport).enqueue(new Callback<ResponseReport>() {
            @Override
            public void onResponse(Call<ResponseReport> call, Response<ResponseReport> response) {
                final ResponseReport responseReport = response.body();
                mReportActivityView.validateReportSuccess(responseReport.getIsSuccess(), responseReport.getCode(), responseReport.getMessage());
            }

            @Override
            public void onFailure(Call<ResponseReport> call, Throwable t) {
                mReportActivityView.validateReportFailure();
            }
        });
    }

    void postReportUser(RequestUserReport requestUserReport) {
        final ReportRetrofitInterface reportRetrofitInterface = getRetrofit().create(ReportRetrofitInterface.class);
        reportRetrofitInterface.postReportUser(requestUserReport).enqueue(new Callback<ResponseReport>() {
            @Override
            public void onResponse(Call<ResponseReport> call, Response<ResponseReport> response) {
                final ResponseReport responseReport = response.body();
                mReportActivityView.validateReportSuccess(responseReport.getIsSuccess(), responseReport.getCode(), responseReport.getMessage());
            }

            @Override
            public void onFailure(Call<ResponseReport> call, Throwable t) {
                mReportActivityView.validateReportFailure();
            }
        });
    }
}
