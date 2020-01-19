package com.e.spectra.dagger.modules.applicationModule;

import com.e.spectra.application.EcatalogueApplication;

import dagger.Module;
import dagger.Provides;

@Module
public class EcatalogueApplicationModule {

    @Provides
    EcatalogueApplication provideEcatalogueApllication(EcatalogueApplication application) {
        return application;
    }
}
