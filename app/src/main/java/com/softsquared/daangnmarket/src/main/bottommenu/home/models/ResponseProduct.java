package com.softsquared.daangnmarket.src.main.bottommenu.home.models;

import com.google.gson.annotations.SerializedName;
import com.softsquared.daangnmarket.src.location.models.ResponseAddress;

import java.io.Serializable;
import java.util.ArrayList;

public class ResponseProduct {
    @SerializedName("result")
    private ArrayList<Result> result;

    @SerializedName("isSuccess")
    private boolean isSuccess;

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

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

    public class Result implements Serializable {
        @SerializedName("productNo")
        private int productNo;
        @SerializedName("imageUrl")
        private String imageUrl;
        @SerializedName("reroll")
        private int reroll;
        @SerializedName("title")
        private String title;
        @SerializedName("address")
        private String address;
        @SerializedName("price")
        private String price;
        @SerializedName("chat")
        private int chat;
        @SerializedName("comments")
        private int comments;
        @SerializedName("favorite")
        private int favorite;

        public int getProductNo() {
            return productNo;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public int getReroll() {
            return reroll;
        }

        public String getTitle() {
            return title;
        }

        public String getAddress() {
            return address;
        }

        public String getPrice() {
            return price;
        }

        public int getChat() {
            return chat;
        }

        public int getComments() {
            return comments;
        }

        public int getFavorite() {
            return favorite;
        }
    }
}
