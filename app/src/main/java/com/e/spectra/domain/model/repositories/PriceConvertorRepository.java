package com.e.spectra.domain.model.repositories;


import androidx.lifecycle.LiveData;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;

public interface PriceConvertorRepository {
    Call<Map<String, String>> convertPrice(String priceConversionName);


}
