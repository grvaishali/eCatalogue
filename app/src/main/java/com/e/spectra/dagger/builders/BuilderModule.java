package com.e.spectra.dagger.builders;


import com.e.spectra.dagger.modules.AuthViewModule;
import com.e.spectra.dagger.modules.BrandViewModule;
import com.e.spectra.dagger.modules.ItemDetailViewModule;
import com.e.spectra.dagger.modules.ItemViewModule;
import com.e.spectra.dagger.modules.RegisterViewModule;
import com.e.spectra.dagger.modules.repositories.BrandRepositoryModule;
import com.e.spectra.dagger.modules.repositories.ItemRepositoryModule;
import com.e.spectra.dagger.modules.repositories.PriceRepositoryModule;
import com.e.spectra.dagger.modules.repositories.UserRepositoryModule;
import com.e.spectra.dagger.modules.services.BrandServiceModule;
import com.e.spectra.dagger.modules.services.PriceServiceModule;
import com.e.spectra.dagger.modules.services.UserServiceModule;
import com.e.spectra.domain.model.repositories.UserRepository;
import com.e.spectra.presentation.ItemDetailsActivity;
import com.e.spectra.presentation.ItemsActivity;
import com.e.spectra.presentation.home.BrandsActivity;
import com.e.spectra.presentation.menu.AddBrandActivity;
import com.e.spectra.presentation.start.LoginActivity;
import com.e.spectra.presentation.start.RegisterActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class BuilderModule {

    @ContributesAndroidInjector(modules = {AuthViewModule.class, UserRepositoryModule.class, UserServiceModule.class})
    abstract LoginActivity contributeLoginActivity();

    @ContributesAndroidInjector(modules = BrandViewModule.class)
    abstract BrandsActivity contributeBrandActivity();

    @ContributesAndroidInjector(modules = {BrandViewModule.class, BrandServiceModule.class, BrandRepositoryModule.class})
    abstract AddBrandActivity contributeAddBrandActivity();

    @ContributesAndroidInjector(modules = {ItemDetailViewModule.class, ItemRepositoryModule.class, PriceServiceModule.class, PriceRepositoryModule.class})
    abstract ItemDetailsActivity contributeItemDetailActivity();

    @ContributesAndroidInjector(modules = RegisterViewModule.class)
    abstract RegisterActivity contributeRegisterActivity();

    @ContributesAndroidInjector(modules = ItemViewModule.class)
    abstract ItemsActivity contributeItemActivity();


}

