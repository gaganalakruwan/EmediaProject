package com.gagana.emediatest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static Retrofit retrofit = null;

    public static Retrofit getLoginClient() {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                //.addInterceptor(new TokenIntercept()) //need to implement
                .build();
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Constant.Root_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(client)
                    .build();
        }else{
            retrofit = new Retrofit.Builder()
                    .baseUrl(Constant.Root_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(client)
                    .build();
        }
        return retrofit;

    }
}
