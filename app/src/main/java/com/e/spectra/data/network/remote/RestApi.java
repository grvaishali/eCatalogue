package com.e.spectra.data.network.remote;

import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RestApi {

    @GET("convert")
    Call<Map<String, String>> getPrice(@Query("q") String q,
                                       @Query("compact") String compact,
                                       @Query("apiKey") String apiKey);
}
