package com.softsquared.daangnmarket.src.join.interfaces;

public interface JoinActivityView {
    void validateJoinSuccess(boolean isSuccess, int code, String message);

    void validateJoinFailure();
}
