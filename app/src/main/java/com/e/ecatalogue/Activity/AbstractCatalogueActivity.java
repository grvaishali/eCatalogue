package com.e.ecatalogue.Activity;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.e.ecatalogue.ContactUsActivityy;
import com.e.ecatalogue.R;

public abstract class AbstractCatalogueActivity extends AppCompatActivity {
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // return super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Fix for back button on menu
            case android.R.id.home:
                onBackPressed();
                return true;

            case R.id.exit:
                finish();
                return true;


            case R.id.home:
                Intent homeIntent = new Intent(AbstractCatalogueActivity.this, BrandsActivity.class);
                startActivity(homeIntent);
                return true;

            case R.id.info:
                startActivity(new Intent(AbstractCatalogueActivity.this, AboutUsActivity.class));

                return true;


            case R.id.termsCondition:
                startActivity(new Intent(AbstractCatalogueActivity.this, TermsConditionActivity.class));
                return true;


            case R.id.contactUs:
                startActivity(new Intent(AbstractCatalogueActivity.this, ContactUsActivityy.class));
                return true;


            case R.id.culture:
                startActivity(new Intent(AbstractCatalogueActivity.this, CultureActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void hideSoftKeyboard() {
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

}
