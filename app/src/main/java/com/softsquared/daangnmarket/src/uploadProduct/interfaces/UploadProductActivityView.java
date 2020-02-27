package com.softsquared.daangnmarket.src.uploadProduct.interfaces;

import com.softsquared.daangnmarket.src.uploadProduct.models.ResponseUploadProduct;
import com.softsquared.daangnmarket.src.uploadProduct.models.ResponseUploadProductCategory;

import java.util.ArrayList;

public interface UploadProductActivityView {
    void validateUploadProductSuccess(boolean isSuccess, int code, String message, ResponseUploadProduct.Result result);

    void validateUploadProductFailure();

    void validateUploadProductImageSuccess(boolean isSuccess, int code, String message, int idx);

    void validateUploadProductImageFailure();

    void validateUploadProductCategorySuccess(boolean isSuccess, int code, String message, ArrayList<ResponseUploadProductCategory.Result> result);

    void validateUploadProductCategoryFailure();

    void removeImage(int idx);
}
