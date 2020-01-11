package com.e.spectra.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.e.spectra.dagger.component.DaggerApplicationComponent;
import com.e.spectra.services.impl.PriceServiceImpl;

import java.util.Map;

import retrofit2.Call;

public class ItemDetailViewModel extends ViewModel {
    public String price = null;

    PriceServiceImpl priceService = DaggerApplicationComponent.create().priceService();


    public Call<Map<String, String>> getCallMap() {
        return priceService.getPrice();
    }
}
