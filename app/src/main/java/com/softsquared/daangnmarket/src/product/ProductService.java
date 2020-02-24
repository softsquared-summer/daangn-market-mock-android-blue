package com.softsquared.daangnmarket.src.product;

import com.softsquared.daangnmarket.src.product.interfaces.ProductActivityView;
import com.softsquared.daangnmarket.src.product.interfaces.ProductRetrofitInterface;
import com.softsquared.daangnmarket.src.product.models.ResponseProduct;
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

            }

            @Override
            public void onFailure(Call<ResponseProduct> call, Throwable t) {

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
                mProductActivityView.validateProductFailure();
            }
        });
    }
}
