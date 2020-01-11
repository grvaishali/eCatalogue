package com.e.spectra.model;


import android.app.Application;
import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

import com.e.spectra.dagger.component.DaggerApplicationComponent;
import com.e.spectra.data.network.remote.RestCallClass;
import com.e.spectra.services.BrandService;


import com.e.spectra.ui.view.BrandListener;
import com.e.spectra.util.ViewUtil;

import java.util.Map;


public class BrandViewModel extends ViewModel {

    public String brandName;
    public BrandListener brandListener;
    BrandService brandService;
    Application application;
    Context context;

    public BrandViewModel() {

    }

    public void onAddBrandClick(View view) {

        context = brandListener.onStarted();
        brandService = DaggerApplicationComponent.builder().build().brandServiceImpl();

        if (null==brandName ||brandName.isEmpty()) {
            brandListener.onFailed("Please enter brandName");
            return;
        }
         brandService.add(brandName, context);
        brandListener.onInsert("Data Inserted");


    }

}
