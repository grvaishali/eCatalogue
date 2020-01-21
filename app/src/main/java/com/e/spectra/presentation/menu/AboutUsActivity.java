package com.e.spectra.presentation.menu;

import android.os.Bundle;

import androidx.lifecycle.ViewModel;

import com.e.spectra.R;
import com.e.spectra.presentation.AbstractCatalogueActivity;

public class AboutUsActivity extends AbstractCatalogueActivity {

    @Override
    public ViewModel getViewModel() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
    }

    }
