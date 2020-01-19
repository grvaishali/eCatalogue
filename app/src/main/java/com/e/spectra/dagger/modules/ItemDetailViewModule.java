package com.e.spectra.dagger.modules;

import androidx.lifecycle.ViewModelProvider;

import com.e.spectra.data.repositories.impl.ItemRepositoryImpl;
import com.e.spectra.data.repositories.impl.PriceConverterRepositoryImpl;
import com.e.spectra.model.ItemDetailViewModel;
import com.e.spectra.util.ViewModelProviderFactory;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ItemDetailViewModule {
    @Provides

    @Named("ItemDetailViewModel")
    ViewModelProvider.Factory provideItemDetailViewModelProvider(ItemDetailViewModel viewModel) {
        return new ViewModelProviderFactory<>(viewModel);
    }

    @Provides
    ItemDetailViewModel providesItemDetailActivityViewModel(PriceConverterRepositoryImpl priceConverterRepository) {
        return new ItemDetailViewModel(priceConverterRepository);
    }

}
