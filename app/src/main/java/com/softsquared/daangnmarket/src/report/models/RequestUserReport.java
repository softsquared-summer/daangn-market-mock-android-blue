package com.softsquared.daangnmarket.src.report.models;

import com.google.gson.annotations.SerializedName;

public class RequestUserReport {
    @SerializedName("reportuserNo")
    int reportuserNo;

    public void setReportuserNo(int reportuserNo) {
        this.reportuserNo = reportuserNo;
    }
}
