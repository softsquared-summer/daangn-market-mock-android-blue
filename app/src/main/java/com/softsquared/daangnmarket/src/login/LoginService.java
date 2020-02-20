package com.softsquared.daangnmarket.src.login;

import com.softsquared.daangnmarket.src.login.interfaces.LoginActivityView;
import com.softsquared.daangnmarket.src.login.interfaces.LoginRetrofitInterface;
import com.softsquared.daangnmarket.src.login.models.LoginResponse;
import com.softsquared.daangnmarket.src.login.models.RequestMessage;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.softsquared.daangnmarket.src.ApplicationClass.getRetrofit;

public class LoginService {
    private final LoginActivityView mLoginActivityView;

    LoginService(final LoginActivityView loginActivityView) {
        this.mLoginActivityView = loginActivityView;
    }

    void postPhone(RequestMessage requestMessage) {
        final LoginRetrofitInterface loginRetrofitInterface = getRetrofit().create(LoginRetrofitInterface.class);
        loginRetrofitInterface.postPhone(requestMessage).enqueue(new Callback<LoginResponse>() {

            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                final LoginResponse loginResponse = response.body();

                mLoginActivityView.validateMessageSuccess(loginResponse.getIsSuccess(), loginResponse.getCode());
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                mLoginActivityView.validateMessageFailure(null);
            }
        });
    }
}
