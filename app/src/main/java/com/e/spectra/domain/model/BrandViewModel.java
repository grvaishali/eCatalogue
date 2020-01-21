package com.e.spectra.model;


import android.app.Application;
import android.content.Context;
import android.view.View;

import androidx.lifecycle.ViewModel;

import com.e.spectra.data.repositories.impl.BrandRepositoryImpl;
import com.e.spectra.services.BrandService;
import com.e.spectra.ui.view.BrandListener;

import javax.inject.Inject;
import javax.inject.Singleton;


public class BrandViewModel extends ViewModel {

    public String brandName;
    public BrandListener brandListener;
    BrandService brandService;
    Application application;
    Context context;
    BrandRepositoryImpl brandRepository;

    @Inject
    public BrandViewModel(BrandRepositoryImpl brandRepository) {
        this.brandRepository = brandRepository;
    }

    public void onAddBrandClick(View view) {

        context = brandListener.onStarted();

        if (null == brandName || brandName.isEmpty()) {
            brandListener.onFailed("Please enter brandName");
            return;
        }
        brandRepository.addBrand(brandName, context);
        brandListener.onInsert("Data Inserted");


    }

}
