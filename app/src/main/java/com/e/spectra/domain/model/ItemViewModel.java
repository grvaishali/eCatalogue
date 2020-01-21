package com.e.spectra.model;

import androidx.lifecycle.ViewModel;

import com.e.spectra.data.repositories.impl.PriceConverterRepositoryImpl;
import com.e.spectra.ui.menu.SettingsActivity;

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
