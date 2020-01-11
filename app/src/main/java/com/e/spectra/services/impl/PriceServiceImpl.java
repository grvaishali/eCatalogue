package com.e.spectra.services.impl;

import androidx.lifecycle.LiveData;

import com.e.spectra.dagger.component.DaggerApplicationComponent;
import com.e.spectra.data.repositories.impl.PriceConverterRepositoryImpl;
import com.e.spectra.services.PriceService;

import java.util.Map;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;

public class PriceServiceImpl implements PriceService {


    @Inject
    PriceServiceImpl() {

    }

    PriceConverterRepositoryImpl priceRepository = DaggerApplicationComponent.create().priceRepository();


    @Override
    public Call<Map<String, String>> getPrice() {
        return priceRepository.convertPrice();
    }


}
