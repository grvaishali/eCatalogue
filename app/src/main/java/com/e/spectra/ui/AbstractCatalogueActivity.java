package com.e.spectra.ui;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.e.spectra.R;
import com.e.spectra.ui.home.BrandsActivity;
import com.e.spectra.ui.menu.AboutUsActivity;
import com.e.spectra.ui.menu.ContactUsActivity;
import com.e.spectra.ui.menu.TermsConditionActivity;
import com.e.spectra.util.LocaleHelper;


public abstract class AbstractCatalogueActivity extends AppCompatActivity {
    private int AddBrand_Request_Code = 1;


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
                startActivity(new Intent(getApplicationContext(), BrandsActivity.class));
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
