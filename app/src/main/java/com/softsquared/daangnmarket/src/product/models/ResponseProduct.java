package com.softsquared.daangnmarket.src.product.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class ResponseProduct {
    @SerializedName("result")
    private Result result;

    @SerializedName("isSuccess")
    private boolean isSuccess;

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    public Result getResult() {
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

    public class Result implements Serializable {
        @SerializedName("userNo")
        private int userNo;
        @SerializedName("productNo")
        private int productNo;
        @SerializedName("id")
        private String id;
        @SerializedName("address")
        private String address;
        @SerializedName("manner")
        private float manner;
        @SerializedName("title")
        private String title;
        @SerializedName("categories")
        private String categories;
        @SerializedName("reroll")
        private int reroll;
        @SerializedName("text")
        private String text;
        @SerializedName("chat")
        private int chat;
        @SerializedName("favorite")
        private int favorite;
        @SerializedName("hits")
        private int hits;
        @SerializedName("price")
        private int price;
    }
}
