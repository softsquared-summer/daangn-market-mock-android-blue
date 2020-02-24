package com.softsquared.daangnmarket.src.main.bottommenu.home.interfaces;

import com.softsquared.daangnmarket.src.location.models.ResponseAddress;
import com.softsquared.daangnmarket.src.main.bottommenu.home.models.ResponseProduct;

import java.util.ArrayList;

public interface HomeFragmentView {
    void validateProductSuccess(boolean isSuccess, int code, String message, ArrayList<ResponseProduct.Result> resultArrayList);

    void validateProductFailure();
}
