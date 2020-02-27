package com.softsquared.daangnmarket.src.main.bottommenu.category.models;

import com.google.gson.annotations.SerializedName;
import com.softsquared.daangnmarket.src.uploadProduct.models.ResponseUploadProductCategory;

import java.util.ArrayList;

public class ResponseCategory {
    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("isSuccess")
    private boolean isSuccess;

    @SerializedName("result")
    private ArrayList<Result> result;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public boolean getIsSuccess() {
        return isSuccess;
    }

    public ArrayList<Result> getResult() {
        return result;
    }

    public class Result {
        @SerializedName("categoriesNo")
        private int categoriesNo;

        @SerializedName("categories")
        private String categories;

        public int getCategoriesNo() {
            return categoriesNo;
        }

        public String getCategories() {
            return categories;
        }
    }
}
