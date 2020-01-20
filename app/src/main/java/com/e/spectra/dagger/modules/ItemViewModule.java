package com.e.spectra.dagger.modules;

import androidx.lifecycle.ViewModelProvider;

import com.e.spectra.data.repositories.impl.PriceConverterRepositoryImpl;
import com.e.spectra.model.ItemViewModel;
import com.e.spectra.util.ViewModelProviderFactory;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class ItemViewModule {

    @Provides

    @Named("ItemViewModel")
    ViewModelProvider.Factory provideItemDetailViewModelProvider(ItemViewModel viewModel) {
        return new ViewModelProviderFactory<>(viewModel);
    }

    @Provides
    ItemViewModel providesItemDetailActivityViewModel(PriceConverterRepositoryImpl priceConverterRepository) {
        return new ItemViewModel(priceConverterRepository);
    }
}
