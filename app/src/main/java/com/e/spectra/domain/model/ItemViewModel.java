package com.e.spectra.domain.model;

import androidx.lifecycle.ViewModel;

import com.e.spectra.domain.model.repositories.impl.PriceConverterRepositoryImpl;
import com.e.spectra.presentation.menu.SettingsActivity;

import java.util.Map;

import retrofit2.Call;

public class ItemViewModel extends ViewModel {
    PriceConverterRepositoryImpl priceConverterRepository;
    public ItemViewModel(PriceConverterRepositoryImpl priceConverterRepository) {
        this.priceConverterRepository=priceConverterRepository;
    }

    public Call<Map<String, String>> getCallMap() {
        return priceConverterRepository.convertPrice(SettingsActivity.PRICE_CONVERSION_NAME);
    }
}
