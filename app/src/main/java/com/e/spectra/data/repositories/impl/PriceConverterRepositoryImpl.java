package com.e.spectra.data.repositories.impl;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.e.spectra.BuildConfig;
import com.e.spectra.dagger.component.DaggerApplicationComponent;
import com.e.spectra.data.network.remote.RestApi;
import com.e.spectra.data.repositories.PriceConvertorRepository;

import java.util.Map;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PriceConverterRepositoryImpl implements PriceConvertorRepository {

    private String convertedPrice;

    @Inject
    PriceConverterRepositoryImpl() {

    }

//

    @Override
    public Call<Map<String, String>> convertPrice() {
        RestApi api = DaggerApplicationComponent.create().getRestCall().creatRestApi();
        Call<Map<String, String>> priceCall = api.getPrice(BuildConfig.priceConversionName, BuildConfig.compact, BuildConfig.apiKey);
        return priceCall;

    }



}
