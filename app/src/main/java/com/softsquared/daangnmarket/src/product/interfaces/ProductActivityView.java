package com.softsquared.daangnmarket.src.product.interfaces;

import com.softsquared.daangnmarket.src.main.bottommenu.home.models.ResponseProduct;
import com.softsquared.daangnmarket.src.product.models.ResponseProductImage;

import java.util.ArrayList;

public interface ProductActivityView {
    void validateProductSuccess(boolean isSuccess, int code, String message, com.softsquared.daangnmarket.src.product.models.ResponseProduct.Result result);

    void validateProductFailure();

    void validateProductImageSuccess(boolean isSuccess, int code, String message, ArrayList<ResponseProductImage.Result> resultArrayList);

    void validateProductImageFailure();
}
