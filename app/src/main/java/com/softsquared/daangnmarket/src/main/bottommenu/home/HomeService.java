package com.softsquared.daangnmarket.src.main.bottommenu.home;

import com.softsquared.daangnmarket.src.location.interfaces.LocationActivityView;
import com.softsquared.daangnmarket.src.main.bottommenu.home.interfaces.HomeFragmentView;
import com.softsquared.daangnmarket.src.main.bottommenu.home.interfaces.HomeRetrofitInterface;
import com.softsquared.daangnmarket.src.main.bottommenu.home.models.ResponseProduct;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.softsquared.daangnmarket.src.ApplicationClass.getRetrofit;

public class HomeService {
    private final HomeFragmentView mHomeFragmentView;

    public HomeService(HomeFragmentView homeFragmentView) {
        mHomeFragmentView = homeFragmentView;
    }

    void getProduct() {
        final HomeRetrofitInterface homeRetrofitInterface = getRetrofit().create(HomeRetrofitInterface.class);
        homeRetrofitInterface.getProduct().enqueue(new Callback<ResponseProduct>() {
            @Override
            public void onResponse(Call<ResponseProduct> call, Response<ResponseProduct> response) {
                final ResponseProduct responseProduct = response.body();
                if (responseProduct.getIsSuccess())
                    mHomeFragmentView.validateProductSuccess(responseProduct.getIsSuccess(), responseProduct.getCode(), responseProduct.getMessage(), responseProduct.getResult());
            }

            @Override
            public void onFailure(Call<ResponseProduct> call, Throwable t) {
                mHomeFragmentView.validateProductFailure();
            }
        });
    }
}
