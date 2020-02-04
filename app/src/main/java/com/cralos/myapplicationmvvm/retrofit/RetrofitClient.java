package com.cralos.myapplicationmvvm.retrofit;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit retrofitInstance;
    private static OkHttpClient okHttpClient;

    private RetrofitClient() {
    }

    public static synchronized Retrofit getRetrofitInstance() {
        if (retrofitInstance == null) {
            okHttpClient = new OkHttpClient.Builder().connectTimeout(45, TimeUnit.SECONDS)
                    .readTimeout(45, TimeUnit.SECONDS)
                    .build();
            retrofitInstance = new Retrofit.Builder()
                    .baseUrl(EndPoints.URL_SERVER)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();
        }
        return retrofitInstance;
    }


}
