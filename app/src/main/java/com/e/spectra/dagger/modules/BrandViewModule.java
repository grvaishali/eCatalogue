package com.e.spectra.dagger.modules;

import androidx.lifecycle.ViewModelProvider;

import com.e.spectra.domain.model.repositories.impl.BrandRepositoryImpl;
import com.e.spectra.domain.model.BrandViewModel;
import com.e.spectra.domain.model.services.BrandService;
import com.e.spectra.util.factory.ViewModelProviderFactory;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class BrandViewModule {


    @Provides
    @Named("BrandViewModel")
    ViewModelProvider.Factory provideBrandViewModelProvider(BrandViewModel viewModel) {
        return new ViewModelProviderFactory<>(viewModel);
    }

    @Provides
    BrandViewModel providesBrandActivityViewModel(BrandService service) {
        return new BrandViewModel(service);
    }
}
