package com.softsquared.daangnmarket.src.join.models;

import com.google.gson.annotations.SerializedName;
import com.softsquared.daangnmarket.src.login.models.LoginResponse;

import java.util.ArrayList;

public class ResponseLogin {
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
        @SerializedName("jwt")
        private String jwt;

        @SerializedName("userNo")
        private ArrayList<UserNo> userNo;

        public String getJwt() {
            return jwt;
        }

        public ArrayList<UserNo> getUserNo() {
            return userNo;
        }

        public class UserNo {
            @SerializedName("userNo")
            private int userNo;

            public int getUserNo() {
                return userNo;
            }
        }
    }
}
