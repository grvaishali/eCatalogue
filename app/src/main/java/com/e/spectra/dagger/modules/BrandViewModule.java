package com.e.spectra.dagger.modules;

import androidx.lifecycle.ViewModelProvider;

import com.e.spectra.data.repositories.impl.BrandRepositoryImpl;
import com.e.spectra.model.BrandViewModel;
import com.e.spectra.util.ViewModelProviderFactory;

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
    BrandViewModel providesBrandActivityViewModel(BrandRepositoryImpl brandRepository) {
        return new BrandViewModel(brandRepository);
    }
}
