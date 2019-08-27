package com.e.ecatalogue.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.e.ecatalogue.ContactUsActivityy;
import com.e.ecatalogue.ECatalogueConstants;
import com.e.ecatalogue.R;
import com.e.ecatalogue.data.ItemData;

public class ItemDetailsActivity extends AppCompatActivity {
    TextView textViewCode, textViewPrice, textViewDescription;
    ImageView imageView;
    ItemData item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        hideSoftKeyboard();
        textViewCode = findViewById(R.id.item_detail_code);
        textViewPrice = findViewById(R.id.item_detail_price);
        textViewDescription = findViewById(R.id.item_detail_description);
        imageView = findViewById(R.id.item_detail_image);
        if (getIntent().hasExtra(ECatalogueConstants.ITEM) && getIntent().hasExtra(ECatalogueConstants.ITEM)) {
            item = (ItemData) getIntent().getSerializableExtra(ECatalogueConstants.ITEM);
            textViewCode.setText(item.getCode());
            textViewPrice.setText(item.getPrice());
            textViewDescription.setText(item.getDescription());
            if (null != item.getImageUrl()) {
                Glide.with(this)
                        .load(item.getImageUrl()).
                        into(imageView);
            }
        }
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
//            case android.R.id.home:
//                startActivity(new Intent(ItemDetailsActivity.this,BrandsActivity.class));
//                return true;

            case R.id.exit:
                finish();
                return true;

            case R.id.home:
                Intent homeIntent = new Intent(ItemDetailsActivity.this, BrandsActivity.class);
                startActivity(homeIntent);
                return true;

            case R.id.info:
                startActivity(new Intent(ItemDetailsActivity.this,AboutUsActivity.class));

                return true;



            case R.id.termsCondition:

                startActivity(new Intent(ItemDetailsActivity.this,TermsConditionActivity.class));
                return true;


            case R.id.contactUs:
                startActivity(new Intent(ItemDetailsActivity.this, ContactUsActivityy.class));
                return true;



            case R.id.culture:
                startActivity(new Intent(ItemDetailsActivity.this, CultureActivity.class));
                return true;

        }
        return super.onOptionsItemSelected(item);
    }
    private void hideSoftKeyboard() {
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

}
