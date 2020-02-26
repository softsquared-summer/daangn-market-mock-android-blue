package com.softsquared.daangnmarket.config;

import android.util.Log;

import androidx.annotation.NonNull;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import static com.softsquared.daangnmarket.src.ApplicationClass.X_ACCESS_TOKEN;
import static com.softsquared.daangnmarket.src.ApplicationClass.sSharedPreferences;

public class XAccessTokenInterceptor implements Interceptor {
    @NotNull
    @Override
    public Response intercept(@NonNull final Interceptor.Chain chain) throws IOException {
        final Request.Builder builder = chain.request().newBuilder();
        final String jwtToken = sSharedPreferences.getString(X_ACCESS_TOKEN, null);

        if (jwtToken != null) {
            builder.addHeader("x-access-token", jwtToken);
            System.out.println("jwtToken == " + jwtToken);
        }
        return chain.proceed(builder.build());
    }
}
