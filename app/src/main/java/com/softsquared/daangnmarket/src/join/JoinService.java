package com.softsquared.daangnmarket.src.join;

import com.softsquared.daangnmarket.src.join.interfaces.JoinActivityView;
import com.softsquared.daangnmarket.src.join.interfaces.JoinRetrofitInterface;
import com.softsquared.daangnmarket.src.join.models.JoinResponse;
import com.softsquared.daangnmarket.src.join.models.RequestJoin;
import com.softsquared.daangnmarket.src.login.interfaces.LoginActivityView;
import com.softsquared.daangnmarket.src.login.interfaces.LoginRetrofitInterface;
import com.softsquared.daangnmarket.src.login.models.LoginResponse;
import com.softsquared.daangnmarket.src.login.models.RequestMessage;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.softsquared.daangnmarket.src.ApplicationClass.getRetrofit;

public class JoinService {
    private final JoinActivityView mJoinActivityView;

    JoinService(final JoinActivityView joinActivityView) {
        this.mJoinActivityView = joinActivityView;
    }

    void postJoin(RequestJoin requestJoin) {
        final JoinRetrofitInterface joinRetrofitInterface = getRetrofit().create(JoinRetrofitInterface.class);
        joinRetrofitInterface.postJoin(requestJoin).enqueue(new Callback<JoinResponse>() {
            @Override
            public void onResponse(Call<JoinResponse> call, Response<JoinResponse> response) {
                final JoinResponse joinResponse = response.body();
                mJoinActivityView.validateJoinSuccess(joinResponse.getIsSuccess(), joinResponse.getCode(), joinResponse.getMessage());
            }

            @Override
            public void onFailure(Call<JoinResponse> call, Throwable t) {
                mJoinActivityView.validateJoinFailure();
            }
        });
    }
}
