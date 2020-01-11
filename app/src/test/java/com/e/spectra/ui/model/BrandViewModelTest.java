package com.e.spectra.ui.model;

import android.view.View;

import com.e.spectra.model.AuthViewModel;
import com.e.spectra.model.BrandViewModel;
import com.e.spectra.services.BrandService;
import com.e.spectra.ui.view.BrandListener;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class BrandViewModelTest {

    @Mock
    BrandListener brandListener;

    @Mock
    View view;

    @Mock
    BrandService brandService;

    @InjectMocks
    private BrandViewModel brand = new BrandViewModel();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testOnAddBrandButtonClickEmptyBrand() {
        //Given
       brand.brandName=null;
        //When
        brand.onAddBrandClick(view);

        //Then
        Mockito.verify(brand.brandListener, Mockito.times(1)).onStarted();
        Mockito.verify(brand.brandListener, Mockito.times(1)).onFailed(Mockito.anyString());
    }

    @Test
    public void testOnLoginButtonClickVanilla() {
        //Given
        brand.brandName="test";

        //When
        brand.onAddBrandClick(view);

        //Then
        Mockito.verify(brand.brandListener, Mockito.times(1)).onStarted();
        Mockito.verify(brand.brandListener, Mockito.times(0)).onFailed(Mockito.anyString());
    }
}
