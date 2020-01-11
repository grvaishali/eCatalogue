package com.e.spectra.data.repositories;


import java.util.Map;

import retrofit2.Callback;

public interface PriceConvertorRepository {
    void convertPrice(Double price, Callback<Map<String, String>> callback);
}
