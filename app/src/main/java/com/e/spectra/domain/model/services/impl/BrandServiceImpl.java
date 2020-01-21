package com.e.spectra.services.impl;

import android.content.Context;

import com.e.spectra.data.repositories.BrandRepository;
import com.e.spectra.data.repositories.impl.BrandRepositoryImpl;
import com.e.spectra.services.BrandService;

import javax.inject.Inject;

public class BrandServiceImpl implements BrandService {

    @Inject
    BrandServiceImpl() {

    }

    BrandRepository brandRepository = new BrandRepositoryImpl();

    @Override
    public void add(String brandName,     Context context) {

        brandRepository.addBrand(brandName, context);

    }
}
