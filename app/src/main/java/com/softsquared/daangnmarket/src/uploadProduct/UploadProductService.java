package com.softsquared.daangnmarket.src.uploadProduct;

import com.softsquared.daangnmarket.src.uploadProduct.interfaces.UploadProductActivityView;
import com.softsquared.daangnmarket.src.uploadProduct.interfaces.UploadProductRetrofitInterface;
import com.softsquared.daangnmarket.src.uploadProduct.models.RequestUploadProduct;
import com.softsquared.daangnmarket.src.uploadProduct.models.RequestUploadProductImage;
import com.softsquared.daangnmarket.src.uploadProduct.models.ResponseUploadProduct;
import com.softsquared.daangnmarket.src.uploadProduct.models.ResponseUploadProductImage;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.softsquared.daangnmarket.src.ApplicationClass.getRetrofit;

public class UploadProductService {
    private final UploadProductActivityView mUploadProductActivityView;

    UploadProductService(final UploadProductActivityView uploadProductActivityView) {
        this.mUploadProductActivityView = uploadProductActivityView;
    }

    void postUploadProduct(RequestUploadProduct requestUploadProduct) {
        final UploadProductRetrofitInterface uploadProductRetrofitInterface = getRetrofit().create(UploadProductRetrofitInterface.class);
        uploadProductRetrofitInterface.postUploadProduct(requestUploadProduct).enqueue(new Callback<ResponseUploadProduct>() {
            @Override
            public void onResponse(Call<ResponseUploadProduct> call, Response<ResponseUploadProduct> response) {
                final ResponseUploadProduct responseUploadProduct = response.body();

                mUploadProductActivityView.validateUploadProductSuccess(responseUploadProduct.getIsSuccess(), responseUploadProduct.getCode(), responseUploadProduct.getMessage(), responseUploadProduct.getResult());
            }

            @Override
            public void onFailure(Call<ResponseUploadProduct> call, Throwable t) {
                mUploadProductActivityView.validateUploadProductFailure();
            }
        });
    }

    void postUploadProductImage(final RequestUploadProductImage requestUploadProductImage, final int idx) {
        final UploadProductRetrofitInterface uploadProductRetrofitInterface = getRetrofit().create(UploadProductRetrofitInterface.class);
        uploadProductRetrofitInterface.postUploadProductImage(requestUploadProductImage).enqueue(new Callback<ResponseUploadProductImage>() {
            @Override
            public void onResponse(Call<ResponseUploadProductImage> call, Response<ResponseUploadProductImage> response) {
                final ResponseUploadProductImage responseUploadProductImage = response.body();

                mUploadProductActivityView.validateUploadProductImageSuccess(responseUploadProductImage.getIsSuccess(), responseUploadProductImage.getCode(), responseUploadProductImage.getMessage(), idx);
            }

            @Override
            public void onFailure(Call<ResponseUploadProductImage> call, Throwable t) {
                mUploadProductActivityView.validateUploadProductImageFailure();
            }
        });
    }
}
