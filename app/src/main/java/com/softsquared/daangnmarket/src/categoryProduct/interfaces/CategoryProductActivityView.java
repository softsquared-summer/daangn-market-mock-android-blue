package com.softsquared.daangnmarket.src.categoryProduct.interfaces;

import com.softsquared.daangnmarket.src.categoryProduct.models.ResponseCategoryProduct;

import java.util.ArrayList;

public interface CategoryProductActivityView {
    void validateCategoryProductSuccess(boolean isSuccess, int code, String message, ArrayList<ResponseCategoryProduct.Result> resultArrayList);

    void validateCategoryProductFailure();
}
