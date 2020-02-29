package com.softsquared.daangnmarket.src.join.interfaces;

import com.softsquared.daangnmarket.src.join.models.ResponseLogin;

public interface JoinActivityView {
    void validateJoinSuccess(boolean isSuccess, int code, String message);

    void validateJoinFailure();

    void validateLoginSuccess(boolean isSuccess, int code, String message, ResponseLogin.Result result);

    void validateLoginFailure();
}
