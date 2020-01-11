package com.e.spectra.services;

import androidx.lifecycle.LiveData;

import java.util.Map;

import retrofit2.Callback;

public interface PriceService {

    public void getPrice(Double price, Callback<Map<String, String>> callback);
}
