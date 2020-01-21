package com.e.spectra.presentation;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModel;

import com.e.spectra.R;
import com.e.spectra.presentation.menu.ContactUsActivity;
import com.e.spectra.presentation.menu.TermsConditionActivity;
import com.e.spectra.presentation.navigation.NavigationActivity;
import com.e.spectra.presentation.menu.AboutUsActivity;
import com.e.spectra.util.LocaleHelper;

import dagger.android.support.DaggerAppCompatActivity;


public abstract class AbstractCatalogueActivity<T extends ViewModel> extends DaggerAppCompatActivity {
    private int AddBrand_Request_Code = 1;
    private T viewModel;

    /**
     * @return view model instance
     */
    public abstract T getViewModel();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.viewModel = viewModel == null ? getViewModel() : viewModel;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void updateViews(String languageCode, Context getContext) {
        Context context = LocaleHelper.setLocale(getContext, languageCode);
        Resources resources = context.getResources();
        recreate();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case R.id.home:
                startActivity(new Intent(getApplicationContext(), NavigationActivity.class));
                return true;


            case R.id.info:
                startActivity(new Intent(getApplicationContext(), AboutUsActivity.class));

                return true;


            case R.id.termsCondition:

                startActivity(new Intent(getApplicationContext(), TermsConditionActivity.class));
                return true;


            case R.id.contactUs:
                startActivity(new Intent(getApplicationContext(), ContactUsActivity.class));
                return true;


            default:
                break;


        }
        return super.onOptionsItemSelected(item);
    }

    public void hideSoftKeyboard() {
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleHelper.onAttach(base));
    }

}
