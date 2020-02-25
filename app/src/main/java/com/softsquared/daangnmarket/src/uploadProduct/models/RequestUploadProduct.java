package com.softsquared.daangnmarket.src.uploadProduct.models;

import com.google.gson.annotations.SerializedName;

public class RequestUploadProduct {
    @SerializedName("title")
    private String title;

    @SerializedName("categoriesNo")
    private String categoriesNo;

    @SerializedName("price")
    private String price;

    @SerializedName("text")
    private String text;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCategoriesNo(String categoriesNo) {
        this.categoriesNo = categoriesNo;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setText(String text) {
        this.text = text;
    }
}
