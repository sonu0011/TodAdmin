package com.adios.ediostoiadmin.data.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ToiAdminApi {

    private static ToiAdminApi instance;
    private static final String BASE_URL = "http://202.164.43.200:58080/RWP_API/";
    private Retrofit mRetrofit;

    public static ToiAdminApi getInstance() {
        if (instance == null) {
            instance = new ToiAdminApi();
        }
        return instance;
    }

    private ToiAdminApi() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public NetworkRequests getAllNetworkRequests() {
        return mRetrofit.create(NetworkRequests.class);
    }
}
