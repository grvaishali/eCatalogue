package com.e.spectra.dagger.component;

import com.e.spectra.dagger.daggerModule.contextModule.ContextModule;

import com.e.spectra.dagger.daggerModule.databaseModule.EcatalogueDatabaseModule;
import com.e.spectra.dagger.daggerModule.networkModule.FirebaseManagerModule;

import com.e.spectra.data.network.remote.RestCallClass;
import com.e.spectra.data.repositories.impl.BrandRepositoryImpl;
import com.e.spectra.data.repositories.impl.PriceConverterRepositoryImpl;
import com.e.spectra.data.repositories.impl.UserRespositoryImpl;

import com.e.spectra.services.impl.BrandServiceImpl;
import com.e.spectra.services.impl.PriceServiceImpl;
import com.e.spectra.services.impl.UserServiceImp;

import dagger.Component;
import dagger.android.AndroidInjectionModule;

@Component(modules = {
        ContextModule.class, AndroidInjectionModule.class,
        FirebaseManagerModule.class, EcatalogueDatabaseModule.class})
public interface ApplicationComponent {

    @Component.Builder
    interface Builder {

        ApplicationComponent build();
    }

    PriceConverterRepositoryImpl priceRepository();

    PriceServiceImpl priceService();

    BrandRepositoryImpl method();

    UserServiceImp userService();

    RestCallClass getRestCall();

    UserRespositoryImpl userRepository();

    BrandRepositoryImpl brandRepositoryImpl();

    BrandServiceImpl brandServiceImpl();


    //  EcatalogueRoomDatabase database();
}
