package com.e.spectra.domain.model;

import androidx.lifecycle.ViewModel;

import com.e.spectra.domain.model.repositories.impl.PriceConverterRepositoryImpl;
import com.e.spectra.presentation.menu.SettingsActivity;

import java.util.Map;

import javax.inject.Inject;

import retrofit2.Call;

public class ItemDetailViewModel extends ViewModel {
    PriceConverterRepositoryImpl priceConverterRepository;

   public String price = null;

  //  private PriceServiceImpl priceService = DaggerApplicationComponent.builder().build().priceService();

    @Inject
    public ItemDetailViewModel(PriceConverterRepositoryImpl priceConverterRepository) {
        this.priceConverterRepository=priceConverterRepository;
    }


    public Call<Map<String, String>> getCallMap() {
        return priceConverterRepository.convertPrice(SettingsActivity.PRICE_CONVERSION_NAME);
    }
}
