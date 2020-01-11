package com.e.spectra.data.repositories.impl;

import com.e.spectra.dagger.component.DaggerApplicationComponent;
import com.e.spectra.data.network.remote.RestApi;
import com.e.spectra.data.repositories.PriceConvertorRepository;
import java.util.Map;
import javax.inject.Inject;
import retrofit2.Call;
import retrofit2.Callback;

public class PriceConverterRepositoryImpl implements PriceConvertorRepository {

    @Inject
    PriceConverterRepositoryImpl() {

    }

    @Override
    public void convertPrice(Double price, Callback<Map<String, String>> callback) {

        RestApi api = DaggerApplicationComponent.create().getRestCall().creatRestApi();
        Call<Map<String, String>> priceCall = api.getPrice("USD_INR", "ultra", "ad7739e4cdc4d5673c4c");
        priceCall.enqueue(callback);


    }

}
