package com.softsquared.daangnmarket.src.location.models;

import com.google.gson.annotations.SerializedName;

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

    public class Result {

        @SerializedName("address")
        private String address;

        public String getAddress() {
            return address;
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
