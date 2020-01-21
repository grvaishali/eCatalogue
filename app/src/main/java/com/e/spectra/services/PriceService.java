package com.e.spectra.services;

import androidx.lifecycle.LiveData;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;

public interface PriceService {

    Call<Map<String, String>> getPrice(String priceConversionName);

  
}
