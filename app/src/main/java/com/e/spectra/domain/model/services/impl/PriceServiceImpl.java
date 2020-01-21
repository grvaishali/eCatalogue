package com.e.spectra.domain.model.services.impl;

import com.e.spectra.domain.model.repositories.impl.PriceConverterRepositoryImpl;
import com.e.spectra.domain.model.services.PriceService;

import java.util.Map;

import javax.inject.Inject;

import retrofit2.Call;

public class PriceServiceImpl implements PriceService {


    @Inject
    PriceServiceImpl() {

    }

    PriceConverterRepositoryImpl priceRepository = new PriceConverterRepositoryImpl();

    @Override
    public Call<Map<String, String>> getPrice(String priceConversionName) {
        return priceRepository.convertPrice(priceConversionName);
    }


}
