package com.techmate.woocommerce.api;

import com.techmate.woocommerce.utils.Constants;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class ApiInterceptor implements Interceptor {

    @NotNull
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request()
                .newBuilder()
                .addHeader("ckey", Constants.CONSUMER_KEY)
                .addHeader("csecret", Constants.CONSUMER_SECRET)
                .build();
        return chain.proceed(request);
    }
}
