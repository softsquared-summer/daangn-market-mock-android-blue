package com.softsquared.daangnmarket.src.anotherAll;

import com.softsquared.daangnmarket.src.anotherAll.interfaces.AnotherAllRetrofitInterface;
import com.softsquared.daangnmarket.src.anotherAll.interfaces.AnotherAllActivityView;
import com.softsquared.daangnmarket.src.anotherAll.models.ResponseProductAnother;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.softsquared.daangnmarket.src.ApplicationClass.getRetrofit;

public class AnotherAllService {
    private AnotherAllActivityView mAnotherAllActivityView;

    public AnotherAllService(AnotherAllActivityView anotherAllActivityView) {
        this.mAnotherAllActivityView = anotherAllActivityView;
    }

    public void getAnotherProductAll(int userNo, int exceptionNo, int page) {
        final AnotherAllRetrofitInterface anotherAllRetrofitInterface = (AnotherAllRetrofitInterface) getRetrofit().create(AnotherAllRetrofitInterface.class);
        anotherAllRetrofitInterface.getAnotherProductAll(userNo, page).enqueue(new Callback<ResponseProductAnother>() {
            @Override
            public void onResponse(Call<ResponseProductAnother> call, Response<ResponseProductAnother> response) {
                final ResponseProductAnother responseProductAnother = response.body();
                mAnotherAllActivityView.validateProductAnotherSuccess(responseProductAnother.getIsSuccess(), responseProductAnother.getCode(), responseProductAnother.getMessage(), responseProductAnother.getResult());
            }

            @Override
            public void onFailure(Call<ResponseProductAnother> call, Throwable t) {
                mAnotherAllActivityView.validateProductAnotherFailure();
            }
        });
    }

    public void getAnotherProductStatus(int userNo, char status, int page) {
        final AnotherAllRetrofitInterface anotherAllRetrofitInterface = (AnotherAllRetrofitInterface) getRetrofit().create(AnotherAllRetrofitInterface.class);
        anotherAllRetrofitInterface.getAnotherProductStatus(userNo, status, page).enqueue(new Callback<ResponseProductAnother>() {
            @Override
            public void onResponse(Call<ResponseProductAnother> call, Response<ResponseProductAnother> response) {
                final ResponseProductAnother responseProductAnother = response.body();
                mAnotherAllActivityView.validateProductAnotherSuccess(responseProductAnother.getIsSuccess(), responseProductAnother.getCode(), responseProductAnother.getMessage(), responseProductAnother.getResult());
            }

            @Override
            public void onFailure(Call<ResponseProductAnother> call, Throwable t) {
                mAnotherAllActivityView.validateProductAnotherFailure();
            }
        });
    }
}
