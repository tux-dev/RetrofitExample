package com.example.android.retrofitexample;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by mher on 3/5/18.
 */

public class MyInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
        System.out.println("===> MyInterceptor response is " + response.toString());
        return response;
    }
}
