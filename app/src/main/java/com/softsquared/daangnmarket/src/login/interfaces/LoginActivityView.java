package com.softsquared.daangnmarket.src.login.interfaces;

import com.softsquared.daangnmarket.src.login.models.LoginResponse;

public interface LoginActivityView {
    void validateMessageSuccess(boolean isSuccess, int code);

    void validateMessageFailure();

    void validatePhoneCertSuccess(boolean isSuccess, int code, String message);

    void validatePhoneCertFailure();

    void validateLoginSuccess(boolean isSuccess, int code, String message, LoginResponse.Result result);

    void validateLoginFailure();
}
