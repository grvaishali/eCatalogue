package com.e.spectra.domain.model;


import android.app.Application;
import android.content.Context;
import android.view.View;

import androidx.lifecycle.ViewModel;

import com.e.spectra.domain.model.repositories.impl.BrandRepositoryImpl;
import com.e.spectra.domain.model.services.BrandService;
import com.e.spectra.presentation.view.BrandListener;

import javax.inject.Inject;


public class BrandViewModel extends ViewModel {

    public String brandName;
    public BrandListener brandListener;
    BrandService brandService;
    Application application;
    Context context;


    @Inject
    public BrandViewModel(BrandService bradService) {
        this.brandService=bradService;
    }

    public void onAddBrandClick(View view) {

        context = brandListener.onStarted();

        if (null == brandName || brandName.isEmpty()) {
            brandListener.onFailed("Please enter brandName");
            return;
        }
        brandService.add(brandName, context);
        brandListener.onInsert("Data Inserted");


    }

}
