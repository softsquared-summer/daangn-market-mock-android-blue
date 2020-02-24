package com.softsquared.daangnmarket.src.join.models;

import com.google.gson.annotations.SerializedName;

public class RequestJoin {
    @SerializedName("id")
    private String id;

    @SerializedName("phoneNum")
    private String phoneNum;

    @SerializedName("locationNo")
    private int locationNo;

    public void setId(String id) {
        this.id = id;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public void setLocationNo(int locationNo) {
        this.locationNo = locationNo;
    }
}
