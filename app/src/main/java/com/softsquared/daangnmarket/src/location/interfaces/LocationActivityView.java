package com.softsquared.daangnmarket.src.location.interfaces;

import com.softsquared.daangnmarket.src.location.models.ResponseAddress;

import java.util.ArrayList;

public interface LocationActivityView {
    void validateLocationSuccess(ArrayList<ResponseAddress.Result> result, boolean isSuccess, int code, String message);

    void validateLocationFailure();
}
