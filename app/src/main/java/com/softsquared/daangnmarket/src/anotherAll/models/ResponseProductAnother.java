package com.softsquared.daangnmarket.src.anotherAll.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class ResponseProductAnother {
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
        @SerializedName("title")
        private String title;
        @SerializedName("price")
        private String price;
        @SerializedName("imageUrl")
        private String imageUrl;
        @SerializedName("text")
        private String text;
        @SerializedName("reroll")
        private int reroll;
        @SerializedName("address")
        private String address;
        @SerializedName("chat")
        private int chat;
        @SerializedName("comment")
        private int comment;
        @SerializedName("favorite")
        private int favorite;

        public int getProductNo() {
            return productNo;
        }

        public String getTitle() {
            return title;
        }

        public String getPrice() {
            return price;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public String getText() {
            return text;
        }

        public int getReroll() {
            return reroll;
        }

        public String getAddress() {
            return address;
        }

        public int getChat() {
            return chat;
        }

        public int getComment() {
            return comment;
        }

        public int getFavorite() {
            return favorite;
        }
    }
}
