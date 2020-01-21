package com.e.spectra.dagger.component;

import com.e.spectra.application.EcatalogueApplication;
import com.e.spectra.dagger.builders.BuilderModule;
import com.e.spectra.dagger.modules.AuthViewModule;
import com.e.spectra.dagger.modules.BrandViewModule;
import com.e.spectra.dagger.modules.ItemDetailViewModule;
import com.e.spectra.dagger.modules.NetworkModule;
import com.e.spectra.dagger.modules.RegisterViewModule;
import com.e.spectra.dagger.modules.applicationModule.ApplicationBindingModule;
import com.e.spectra.dagger.modules.applicationModule.ApplicationModule;
import com.e.spectra.dagger.modules.applicationModule.EcatalogueApplicationModule;
import com.e.spectra.dagger.modules.databaseModule.EcatalogueDatabaseModule;
import com.e.spectra.dagger.modules.repositories.BrandRepositoryModule;
import com.e.spectra.dagger.modules.repositories.ItemRepositoryModule;
import com.e.spectra.dagger.modules.repositories.PriceRepositoryModule;
import com.e.spectra.dagger.modules.repositories.UserRepositoryModule;
import com.e.spectra.dagger.modules.services.BrandServiceModule;
import com.e.spectra.dagger.modules.services.PriceServiceModule;
import com.e.spectra.dagger.modules.services.UserServiceModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class, ApplicationModule.class,
        EcatalogueDatabaseModule.class, BuilderModule.class, NetworkModule.class, BrandServiceModule.class,
        PriceServiceModule.class, UserServiceModule.class, PriceRepositoryModule.class, UserRepositoryModule.class,
        ItemRepositoryModule.class,
        BrandViewModule.class, AuthViewModule.class, RegisterViewModule.class,
        ItemDetailViewModule.class, ApplicationBindingModule.class, EcatalogueApplicationModule.class})
public interface ApplicationComponent extends AndroidInjector<EcatalogueApplication> {
    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<EcatalogueApplication> {
    }
}




