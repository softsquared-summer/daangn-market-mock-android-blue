package com.softsquared.daangnmarket.src.location.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ResponseAddress {
    @SerializedName("result")
    private ArrayList<Result> result;

    @SerializedName("isSuccess")
    private boolean isSuccess;

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    public class Result implements Serializable {

        @SerializedName("address")
        private String address;

        @SerializedName("locationNo")
        private String locationNo;

        public String getAddress() {
            return address;
        }

        public String getLocationNo() {
            return locationNo;
        }
    }

    public ArrayList<Result> getResult() {
        return result;
    }

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
