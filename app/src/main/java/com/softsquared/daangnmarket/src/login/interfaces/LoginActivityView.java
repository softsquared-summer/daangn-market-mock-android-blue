package com.softsquared.daangnmarket.src.login.interfaces;

public interface LoginActivityView {
    void validateMessageSuccess(boolean isSuccess, int code);

    void validateMessageFailure(String message);
}
