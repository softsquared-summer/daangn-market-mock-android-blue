package com.softsquared.daangnmarket.src.login.models;

import com.google.gson.annotations.SerializedName;

public class RequestLogin {
    @SerializedName("phoneNum")
    private String phoneNum;

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
}
