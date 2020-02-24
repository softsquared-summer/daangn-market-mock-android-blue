package com.softsquared.daangnmarket.src.login.interfaces;

public interface LoginActivityView {
    void validateMessageSuccess(boolean isSuccess, int code);

    void validateMessageFailure();

    void validatePhoneCertSuccess(boolean isSuccess, int code, String message);

    void validatePhoneCertFailure();

    void validateLoginSuccess(boolean isSuccess, int code, String message, String jwt);

    void validateLoginFailure();
}
