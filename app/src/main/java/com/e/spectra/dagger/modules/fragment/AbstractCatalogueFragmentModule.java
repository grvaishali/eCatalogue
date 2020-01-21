package com.e.spectra.dagger.modules.fragment;

import androidx.lifecycle.ViewModelProvider;

import com.e.spectra.presentation.fragments.AbstractCatalogueFragment;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class AbstractCatalogueFragmentModule {

    @Provides
    @Named("Fragment")
    ViewModelProvider.Factory provideAbstractCatalogueFragmentProvider() {
        return (ViewModelProvider.Factory) new AbstractCatalogueFragment();
    }

}
