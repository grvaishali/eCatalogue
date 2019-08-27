package com.e.ecatalogue.Activity;

import android.content.Intent;
import android.os.Bundle;

import com.e.ecatalogue.ContactUsActivityy;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NavUtils;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.e.ecatalogue.R;

public class CultureActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_culture);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // return super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case R.id.home:
                startActivity(new Intent(CultureActivity.this, BrandsActivity.class));

                return true;

            case R.id.exit:
                finish();
                return true;

            case R.id.info:
                startActivity(new Intent(CultureActivity.this, AboutUsActivity.class));

                return true;


            case R.id.termsCondition:

                startActivity(new Intent(CultureActivity.this, TermsConditionActivity.class));
                return true;


            case R.id.contactUs:
                startActivity(new Intent(CultureActivity.this, ContactUsActivityy.class));
                return true;


            case R.id.culture:
                startActivity(new Intent(CultureActivity.this, CultureActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);

    }
}
