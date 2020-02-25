package com.softsquared.daangnmarket.src.uploadProduct.models;

import com.google.gson.annotations.SerializedName;
import com.softsquared.daangnmarket.src.login.models.LoginResponse;

import java.util.ArrayList;

public class ResponseUploadProduct {
    @SerializedName("result")
    private Result result;

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("isSuccess")
    private boolean isSuccess;

    public Result getResult() {
        return result;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public boolean getIsSuccess() {
        return isSuccess;
    }

    public class Result {

        @SerializedName("productNo")
        private ArrayList<ProductNo> productNo;

        public ArrayList<ProductNo> getProductNo() {
            return productNo;
        }

        public class ProductNo {
            @SerializedName("productNo")
            private int productNo;

            public int getProductNo() {
                return productNo;
            }
        }
    }
}
