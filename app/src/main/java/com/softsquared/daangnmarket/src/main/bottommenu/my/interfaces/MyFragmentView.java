package com.softsquared.daangnmarket.src.main.bottommenu.my.interfaces;

import com.softsquared.daangnmarket.src.main.bottommenu.my.models.ResponseProfile;

import java.util.ArrayList;

public interface MyFragmentView {
    void validateProfileSuccess(boolean isSuccess, int code, String message, ArrayList<ResponseProfile.Result> resultArrayList);

    void validateProfileFailure();
}
