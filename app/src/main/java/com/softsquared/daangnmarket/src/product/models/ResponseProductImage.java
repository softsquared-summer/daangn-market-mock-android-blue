package com.softsquared.daangnmarket.src.product.models;

import com.google.gson.annotations.SerializedName;
import com.softsquared.daangnmarket.src.main.bottommenu.home.models.ResponseProduct;

import java.io.Serializable;
import java.util.ArrayList;

public class ResponseProductImage {
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
        @SerializedName("imageIndex")
        private int imageIndex;
        @SerializedName("imageUrl")
        private String imageUrl;

        public int getImageIndex() {
            return imageIndex;
        }

        public String getImageUrl() {
            return imageUrl;
        }
    }
}
