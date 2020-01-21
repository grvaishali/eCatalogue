package com.e.spectra.dagger.modules.services;

import com.e.spectra.domain.model.repositories.BrandRepository;
import com.e.spectra.domain.model.services.BrandService;
import com.e.spectra.domain.model.services.impl.BrandServiceImpl;

import dagger.Module;
import dagger.Provides;

@Module
public class BrandServiceModule {
    BrandServiceImpl brandService;

    @Provides
    BrandService providesBrandServiceModel(BrandRepository brand) {
        return new BrandServiceImpl(brand);


    }
}
