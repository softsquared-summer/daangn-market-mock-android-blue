package com.softsquared.daangnmarket.src.uploadProduct.interfaces;

import com.softsquared.daangnmarket.src.uploadProduct.models.ResponseUploadProduct;

import java.util.ArrayList;

public interface UploadProductActivityView {
    void validateUploadProductSuccess(boolean isSuccess, int code, String message, ResponseUploadProduct.Result result);

    void validateUploadProductFailure();

    void validateUploadProductImageSuccess(boolean isSuccess, int code, String message, int idx);

    void validateUploadProductImageFailure();
}
