package com.softsquared.daangnmarket.src.profile;

import com.softsquared.daangnmarket.src.product.interfaces.ProductRetrofitInterface;
import com.softsquared.daangnmarket.src.product.models.ResponseProduct;
import com.softsquared.daangnmarket.src.profile.interfaces.ProfileActivityView;
import com.softsquared.daangnmarket.src.profile.interfaces.ProfileRetrofitInterface;
import com.softsquared.daangnmarket.src.profile.models.RequestChangeProfile;
import com.softsquared.daangnmarket.src.profile.models.ResponseChangeProfile;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.softsquared.daangnmarket.src.ApplicationClass.getRetrofit;

public class ProfileService {
    private final ProfileActivityView mProfileActivityView;

    public ProfileService(ProfileActivityView profileActivityView) {
        mProfileActivityView = profileActivityView;
    }

    void patchProfile(RequestChangeProfile requestChangeProfile) {
        final ProfileRetrofitInterface profileRetrofitInterface = getRetrofit().create(ProfileRetrofitInterface.class);
        profileRetrofitInterface.patchProfile(requestChangeProfile).enqueue(new Callback<ResponseChangeProfile>() {
            @Override
            public void onResponse(Call<ResponseChangeProfile> call, Response<ResponseChangeProfile> response) {
                final ResponseChangeProfile responseChangeProfile = response.body();
                mProfileActivityView.validateChangeProfileSuccess(responseChangeProfile.getIsSuccess(), responseChangeProfile.getCode(), responseChangeProfile.getMessage());
            }

            @Override
            public void onFailure(Call<ResponseChangeProfile> call, Throwable t) {
                mProfileActivityView.validateChangeProfileFailure();
            }
        });
    }
}
