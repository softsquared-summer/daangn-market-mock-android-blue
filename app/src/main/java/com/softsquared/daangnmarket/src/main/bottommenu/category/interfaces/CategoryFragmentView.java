package com.softsquared.daangnmarket.src.main.bottommenu.category.interfaces;

import com.softsquared.daangnmarket.src.main.bottommenu.category.models.ResponseCategory;

import java.util.ArrayList;

public interface CategoryFragmentView {
    void validateCategorySuccess(boolean isSuccess, int code, String message, ArrayList<ResponseCategory.Result> result);

    void validateCategoryFailure();
}
