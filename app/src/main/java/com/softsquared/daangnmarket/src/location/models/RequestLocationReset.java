package com.softsquared.daangnmarket.src.location.models;

import com.google.gson.annotations.SerializedName;

public class RequestLocationReset {
    @SerializedName("locationNo")
    private int locationNo;

    public void setLocationNo(int locationNo) {
        this.locationNo = locationNo;
    }
}
