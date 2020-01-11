package com.e.spectra.dagger.daggerModule;


import com.e.spectra.dagger.daggerModule.databaseModule.EcatalogueDatabaseModule;
import com.e.spectra.model.BrandViewModel;
import com.e.spectra.ui.home.BrandsActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class BuilderModule {
    @ContributesAndroidInjector(modules = EcatalogueDatabaseModule.class)
    abstract BrandsActivity bindBrandActivity();



}

