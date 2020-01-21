package com.e.spectra.dagger.builders.fragment;

import com.e.spectra.dagger.modules.fragment.AbstractCatalogueFragmentModule;
import com.e.spectra.presentation.fragments.AbstractCatalogueFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector(modules = AbstractCatalogueFragmentModule.class)
    @PerFragment
    abstract AbstractCatalogueFragment contributesAbstractCatalogueFragment();
}
