package com.softsquared.daangnmarket.src.main.bottommenu.my;

import com.softsquared.daangnmarket.src.main.bottommenu.my.interfaces.MyFragmentView;
import com.softsquared.daangnmarket.src.main.bottommenu.my.interfaces.MyRetrofitInterface;
import com.softsquared.daangnmarket.src.main.bottommenu.my.models.ResponseProfile;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.softsquared.daangnmarket.src.ApplicationClass.getRetrofit;

public class MyService {
    private MyFragmentView mMyFragmentView;

    public MyService(MyFragmentView myFragmentView) {
        mMyFragmentView = myFragmentView;
    }

    void getProfile(int userNo) {
        final MyRetrofitInterface myRetrofitInterface = getRetrofit().create(MyRetrofitInterface.class);
        myRetrofitInterface.getProfile(userNo).enqueue(new Callback<ResponseProfile>() {
            @Override
            public void onResponse(Call<ResponseProfile> call, Response<ResponseProfile> response) {
                final ResponseProfile responseProfile = response.body();
                mMyFragmentView.validateProfileSuccess(responseProfile.getIsSuccess(), responseProfile.getCode(), responseProfile.getMessage(), responseProfile.getResult());
            }

            @Override
            public void onFailure(Call<ResponseProfile> call, Throwable t) {
                mMyFragmentView.validateProfileFailure();
            }
        });
    }
}
