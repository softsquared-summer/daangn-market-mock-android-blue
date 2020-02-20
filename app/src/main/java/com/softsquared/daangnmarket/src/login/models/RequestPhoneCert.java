package com.softsquared.daangnmarket.src.login.models;

import com.google.gson.annotations.SerializedName;

public class RequestPhoneCert {
    @SerializedName("phoneNum")
    private String phoneNum;

    @SerializedName("certNo")
    private int certNo;

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public void setCertNo(int certNo) {
        this.certNo = certNo;
    }
}
