package com.e.spectra.ui;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.e.spectra.R;
import com.e.spectra.ui.home.BrandsActivity;
import com.e.spectra.ui.menu.AboutUsActivity;
import com.e.spectra.ui.menu.AddBrandActivity;
import com.e.spectra.ui.menu.ContactUsActivity;
import com.e.spectra.ui.menu.SettingsActivity;
import com.e.spectra.ui.menu.TermsConditionActivity;

public abstract class AbstractCatalogueActivity extends AppCompatActivity {
    private int AddBrand_Request_Code=1;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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


            case R.id.addBrand:
                startActivityForResult(new Intent(getApplicationContext(), AddBrandActivity.class),AddBrand_Request_Code);
                return true;

            case R.id.settings:
                startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
                return true;


        }
        return super.onOptionsItemSelected(item);
    }

    public void hideSoftKeyboard() {
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

}
