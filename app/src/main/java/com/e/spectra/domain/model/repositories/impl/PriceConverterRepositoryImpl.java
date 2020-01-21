package com.e.spectra.domain.model.repositories.impl;

import com.e.spectra.BuildConfig;
import com.e.spectra.data.network.remote.RestApi;
import com.e.spectra.domain.model.repositories.PriceConvertorRepository;

import java.util.Map;

import javax.inject.Inject;

import retrofit2.Call;

public class PriceConverterRepositoryImpl implements PriceConvertorRepository {

    private String convertedPrice;

    @Inject
    RestApi api;

    @Inject
    public PriceConverterRepositoryImpl() {

    }



    @Override
    public Call<Map<String, String>> convertPrice(String priceConversionName) {

       Call<Map<String, String>> priceCall = api.getPrice(priceConversionName, BuildConfig.compact, BuildConfig.apiKey);
        return priceCall;

    }



}
