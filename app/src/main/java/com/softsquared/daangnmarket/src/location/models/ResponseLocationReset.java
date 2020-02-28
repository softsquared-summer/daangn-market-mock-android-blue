package com.softsquared.daangnmarket.src.location.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ResponseLocationReset {
    @SerializedName("isSuccess")
    private boolean isSuccess;

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    public boolean getIsSuccess() {
        return isSuccess;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
