package com.e.spectra.dagger.builders;


import com.e.spectra.dagger.modules.AuthViewModule;
import com.e.spectra.dagger.modules.BrandViewModule;
import com.e.spectra.dagger.modules.ItemDetailViewModule;
import com.e.spectra.dagger.modules.RegisterViewModule;
import com.e.spectra.dagger.modules.databaseModule.EcatalogueDatabaseModule;
import com.e.spectra.ui.ItemDetailsActivity;
import com.e.spectra.ui.home.BrandsActivity;
import com.e.spectra.ui.start.LoginActivity;
import com.e.spectra.ui.start.RegisterActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class BuilderModule {
//    @ContributesAndroidInjector(modules = EcatalogueDatabaseModule.class)
//    abstract BrandsActivity bindBrandActivity();

    @ContributesAndroidInjector(modules = AuthViewModule.class)
    abstract LoginActivity contributeLoginActivity();

    @ContributesAndroidInjector(modules = BrandViewModule.class)
    abstract BrandsActivity contributeBrandActivity();

    @ContributesAndroidInjector(modules = ItemDetailViewModule.class)
    abstract ItemDetailsActivity contributeItemDetailActivity();

    @ContributesAndroidInjector(modules = RegisterViewModule.class)
    abstract RegisterActivity contributeRegisterActivity();





}

