package com.e.spectra.dagger.component;

import com.e.spectra.application.EcatalogueApplication;
import com.e.spectra.dagger.builders.BuilderModule;
import com.e.spectra.dagger.modules.AuthViewModule;
import com.e.spectra.dagger.modules.BrandViewModule;
import com.e.spectra.dagger.modules.ItemDetailViewModule;
import com.e.spectra.dagger.modules.RegisterViewModule;
import com.e.spectra.dagger.modules.applicationModule.ApplicationBindingModule;
import com.e.spectra.dagger.modules.applicationModule.ApplicationModule;
import com.e.spectra.dagger.modules.applicationModule.EcatalogueApplicationModule;
import com.e.spectra.dagger.modules.databaseModule.EcatalogueDatabaseModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class, ApplicationModule.class,
        EcatalogueDatabaseModule.class, BuilderModule.class,
        BrandViewModule.class, AuthViewModule.class, RegisterViewModule.class,
        ItemDetailViewModule.class, ApplicationBindingModule.class, EcatalogueApplicationModule.class})
 public interface ApplicationComponent extends AndroidInjector<EcatalogueApplication> {
    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<EcatalogueApplication> {
    }
}




