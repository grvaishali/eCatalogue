package com.e.spectra.domain.model.services.impl;

import android.content.Context;

import com.e.spectra.domain.model.repositories.BrandRepository;
import com.e.spectra.domain.model.repositories.impl.BrandRepositoryImpl;
import com.e.spectra.domain.model.services.BrandService;

import javax.inject.Inject;

public class BrandServiceImpl implements BrandService {
    BrandRepository brandRepository;

    @Inject
    public BrandServiceImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }


    @Override
    public void add(String brandName, Context context) {

        brandRepository.addBrand(brandName, context);

    }
}
