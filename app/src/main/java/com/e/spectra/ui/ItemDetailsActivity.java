package com.e.spectra.ui;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.e.spectra.constants.ECatalogueConstants;
import com.e.spectra.R;
import com.e.spectra.ui.data.ItemData;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemDetailsActivity extends AbstractCatalogueActivity {
    @BindView(R.id.item_detail_code)
    TextView textViewCode;
    @BindView(R.id.item_detail_price)
    TextView textViewPrice;
    @BindView(R.id.item_detail_description)
    TextView textViewDescription;
    @BindView(R.id.item_detail_image)
    ImageView imageView;
    ItemData item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ButterKnife.bind(this);
        hideSoftKeyboard();
        if (getIntent().hasExtra(ECatalogueConstants.ITEM) && (getIntent().hasExtra(ECatalogueConstants.ITEM))) {
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
    public void hideSoftKeyboard() {
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

}
