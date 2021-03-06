package com.softsquared.daangnmarket.src.product;

import com.softsquared.daangnmarket.src.product.interfaces.ProductActivityView;
import com.softsquared.daangnmarket.src.product.interfaces.ProductRetrofitInterface;
import com.softsquared.daangnmarket.src.product.models.ResponseProduct;
import com.softsquared.daangnmarket.src.product.models.ResponseProductAnother;
import com.softsquared.daangnmarket.src.product.models.ResponseProductImage;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.softsquared.daangnmarket.src.ApplicationClass.getRetrofit;

public class ProductService {
    private final ProductActivityView mProductActivityView;

    public ProductService(ProductActivityView productActivityView) {
        mProductActivityView = productActivityView;
    }

    void getProduct(int productNo) {
        final ProductRetrofitInterface productRetrofitInterface = getRetrofit().create(ProductRetrofitInterface.class);
        productRetrofitInterface.getProduct(productNo).enqueue(new Callback<ResponseProduct>() {
            @Override
            public void onResponse(Call<ResponseProduct> call, Response<ResponseProduct> response) {
                final ResponseProduct responseProduct = response.body();
                if (responseProduct.getIsSuccess())
                    mProductActivityView.validateProductSuccess(responseProduct.getIsSuccess(), responseProduct.getCode(), responseProduct.getMessage(), responseProduct.getResult());
            }

            @Override
            public void onFailure(Call<ResponseProduct> call, Throwable t) {
                mProductActivityView.validateProductFailure();
            }
        });
    }

    void getProductImage(int productNo) {
        final ProductRetrofitInterface productRetrofitInterface = getRetrofit().create(ProductRetrofitInterface.class);
        productRetrofitInterface.getProductImage(productNo).enqueue(new Callback<ResponseProductImage>() {
            @Override
            public void onResponse(Call<ResponseProductImage> call, Response<ResponseProductImage> response) {
                final ResponseProductImage responseProductImage = response.body();
                if (responseProductImage.getIsSuccess())
                    mProductActivityView.validateProductImageSuccess(responseProductImage.getIsSuccess(), responseProductImage.getCode(), responseProductImage.getMessage(), responseProductImage.getResult());
            }

            @Override
            public void onFailure(Call<ResponseProductImage> call, Throwable t) {
                mProductActivityView.validateProductImageFailure();
            }
        });
    }

    void getAnotherProduct(int userNo, int exceptionNo, int page) {
        final ProductRetrofitInterface productRetrofitInterface = getRetrofit().create(ProductRetrofitInterface.class);
        productRetrofitInterface.getAnotherProduct(userNo, exceptionNo, page).enqueue(new Callback<ResponseProductAnother>() {
            @Override
            public void onResponse(Call<ResponseProductAnother> call, Response<ResponseProductAnother> response) {
                final ResponseProductAnother responseProductAnother = response.body();
                mProductActivityView.validateProductAnotherSuccess(responseProductAnother.getIsSuccess(), responseProductAnother.getCode(), responseProductAnother.getMessage(), responseProductAnother.getResult());
            }

            @Override
            public void onFailure(Call<ResponseProductAnother> call, Throwable t) {
                mProductActivityView.validateProductAnotherFailure();
            }
        });
    }
}
