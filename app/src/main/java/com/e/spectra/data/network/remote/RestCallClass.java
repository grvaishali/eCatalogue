package com.e.spectra.data.network.remote;

import com.e.spectra.constants.RemoteConstants;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestCallClass {
    Retrofit retrofit;

    @Inject
    RestCallClass() {

    }


    private void getInstance() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        retrofit = new Retrofit.Builder().baseUrl(RemoteConstants.baseUrl).client(client).addConverterFactory(GsonConverterFactory.create()).build();

    }

    public RestApi creatRestApi() {
        getInstance();
        RestApi api = retrofit.create(RestApi.class);
        return api;
    }

    public Map<String, String> getPrice(Double userPrice) {
        Map<String, String> priceConverter = new HashMap<>();
        final Call<Map<String, String>> price = creatRestApi().getPrice("USD_INR", "ultra", "ad7739e4cdc4d5673c4c");
//        Call call = client.newCall(price);
        price.enqueue(new Callback<Map<String, String>>() {
            @Override
            public void onResponse(Call<Map<String, String>> call, Response<Map<String, String>> response) {
                priceConverter.putAll(response.body());
            }

            @Override
            public void onFailure(Call<Map<String, String>> call, Throwable t) {
                t.printStackTrace();
            }
        });
        return priceConverter;
    }
}
