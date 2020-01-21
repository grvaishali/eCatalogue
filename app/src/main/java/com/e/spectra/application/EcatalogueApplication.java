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
        HashMap<String,String> hashMap=new HashMap<String,String>();
        hashMap.put("INR_USD","70.0");
        hashMap.put("USD_INR","0.40");
        int i;


        return hashMap;
    }

    protected void onPostExecute(Long result) {

    }
}
