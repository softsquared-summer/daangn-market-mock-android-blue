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
        @SerializedName("profileUrl")
        private String profileUrl;
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

        public int getUserNo() {
            return userNo;
        }

        public int getProductNo() {
            return productNo;
        }

        public String getId() {
            return id;
        }

        public String getAddress() {
            return address;
        }

        public float getManner() {
            return manner;
        }

        public String getTitle() {
            return title;
        }

        public String getCategories() {
            return categories;
        }

        public int getReroll() {
            return reroll;
        }

        public String getText() {
            return text;
        }

        public int getChat() {
            return chat;
        }

        public int getFavorite() {
            return favorite;
        }

        public int getHits() {
            return hits;
        }

        public int getPrice() {
            return price;
        }

        public String getProfileUrl() {
            return profileUrl;
        }
    }
}
