package com.softsquared.daangnmarket.src.profile.models;

import com.google.gson.annotations.SerializedName;

public class RequestChangeProfile {
    @SerializedName("profileUrl")
    private String profileUrl;

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }
}
