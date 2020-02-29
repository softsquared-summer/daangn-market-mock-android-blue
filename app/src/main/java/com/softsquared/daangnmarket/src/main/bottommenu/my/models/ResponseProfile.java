package com.softsquared.daangnmarket.src.main.bottommenu.my.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ResponseProfile {
    @SerializedName("result")
    ArrayList<Result> result;

    @SerializedName("isSuccess")
    private boolean isSuccess;

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    public boolean getIsSuccess() {
        return isSuccess;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public ArrayList<Result> getResult() {
        return result;
    }

    public class Result {
        @SerializedName("userNo")
        private int userNo;

        @SerializedName("profileUrl")
        private String profileUrl;

        @SerializedName("id")
        private String id;

        @SerializedName("manner")
        private Float manner;

        @SerializedName("createAt")
        private String createAt;

        @SerializedName("address")
        private String address;

        @SerializedName("cert")
        private int cert;

        public int getUserNo() {
            return userNo;
        }

        public String getProfileUrl() {
            return profileUrl;
        }

        public String getId() {
            return id;
        }

        public Float getManner() {
            return manner;
        }

        public String getCreateAt() {
            return createAt;
        }

        public String getAddress() {
            return address;
        }

        public int getCert() {
            return cert;
        }
    }
}
