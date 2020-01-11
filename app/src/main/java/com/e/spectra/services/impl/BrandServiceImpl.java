package com.e.spectra.services.impl;

import android.content.Context;

import com.e.spectra.dagger.component.DaggerApplicationComponent;
import com.e.spectra.data.repositories.BrandRepository;
import com.e.spectra.services.BrandService;

import javax.inject.Inject;

public class BrandServiceImpl implements BrandService {

    @Inject
    BrandServiceImpl() {

    }

    BrandRepository brandRepository = DaggerApplicationComponent.builder().build().brandRepositoryImpl();

    @Override
    public void add(String brandName,     Context context) {

        brandRepository.addBrand(brandName, context);

    }
}
