package com.softsquared.daangnmarket.src.location.interfaces;

public interface LocationRecyclerViewAdapterView {
    void validateLocationResetSuccess(boolean isSuccess, int code, String message);

    void validateLocationResetFailure();
}
