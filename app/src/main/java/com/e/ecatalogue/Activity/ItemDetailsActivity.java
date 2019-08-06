package com.e.ecatalogue.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
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
}
