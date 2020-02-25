package com.softsquared.daangnmarket.src.uploadProduct.models;

import com.google.gson.annotations.SerializedName;

public class RequestUploadProductImage {
    @SerializedName("imageUrl")
    private String imageUrl;

    @SerializedName("imageIndex")
    private int imageIndex;

    @SerializedName("productNo")
    private int productNo;

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setImageIndex(int imageIndex) {
        this.imageIndex = imageIndex;
    }

    public void setProductNo(int productNo) {
        this.productNo = productNo;
    }
}
