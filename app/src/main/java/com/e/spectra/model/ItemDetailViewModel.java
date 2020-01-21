package com.e.spectra.model;

import androidx.lifecycle.ViewModel;

import com.e.spectra.data.repositories.impl.PriceConverterRepositoryImpl;
import com.e.spectra.ui.menu.SettingsActivity;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

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
