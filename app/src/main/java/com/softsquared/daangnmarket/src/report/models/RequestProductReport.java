package com.softsquared.daangnmarket.src.report.models;

import com.google.gson.annotations.SerializedName;

public class RequestProductReport {
    @SerializedName("productNo")
    int productNo;

    public void setProductNo(int productNo) {
        this.productNo = productNo;
    }
}
