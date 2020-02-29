package com.softsquared.daangnmarket.src.profile.interfaces;

import java.util.ArrayList;

public interface ProfileActivityView {
    void validateChangeProfileSuccess(boolean isSuccess, int code, String message);

    void validateChangeProfileFailure();
}
