package com.e.spectra.dagger.modules.repositories;

import com.e.spectra.domain.model.repositories.BrandRepository;
import com.e.spectra.domain.model.repositories.impl.BrandRepositoryImpl;

import dagger.Module;
import dagger.Provides;

@Module
public class BrandRepositoryModule {

    @Provides
    BrandRepository providesBrandRepositoryImplModel() {
        return new BrandRepositoryImpl();
    }
}
