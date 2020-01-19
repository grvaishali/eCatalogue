package com.e.spectra.application;


import android.content.Context;

import com.e.spectra.dagger.component.ApplicationComponent;
import com.e.spectra.dagger.component.DaggerApplicationComponent;
import com.e.spectra.data.repositories.impl.PriceConverterRepositoryImpl;
import com.e.spectra.util.LocaleHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EcatalogueApplication extends dagger.android.support.DaggerApplication {
    static Call<Map<String, String>> map;
    static PriceConverterRepositoryImpl repository;
    private static EcatalogueApplication instance;
    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();



    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerApplicationComponent.builder().create(this);

    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleHelper.onAttach(base, "en"));
    }

    public static ApplicationComponent getApplicationComponetInstance() {
        return instance.getAppComponent();
    }

    private ApplicationComponent getAppComponent() {
        if (applicationComponent == null) {
         //   applicationComponent = DaggerApplicationComponent.builder().build();
        }
        return applicationComponent;
    }

    public static Map setCurrencyPrice() {
        ArrayList list = new ArrayList();
        list.add("INR_USD");
        list.add("USD_INR");
        Map currencyPrice = new HashMap();
        int i;

        for (i = 0; i < list.size(); i++) {
            map = repository.convertPrice(list.get(i).toString());
            map.enqueue(new Callback<Map<String, String>>() {
                int j = 0;

                @Override
                public void onResponse(Call<Map<String, String>> call, Response<Map<String, String>> response) {
                    if (response.isSuccessful()) {

                        currencyPrice.put(list.get(j), response.body().get(list.get(j)));
                        j++;

                    } else {
                        // error response, no access to resource?
                    }
                }

                @Override
                public void onFailure(Call<Map<String, String>> call, Throwable t) {

                }
            });
        }
        return currencyPrice;
    }

    protected void onPostExecute(Long result) {

    }
}
