package com.e.spectra.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.e.spectra.constants.ECatalogueConstants;
import com.e.spectra.R;
import com.e.spectra.databinding.ActivityItemDetailsBinding;
import com.e.spectra.model.BrandViewModel;
import com.e.spectra.model.ItemDetailViewModel;
import com.e.spectra.ui.data.ItemData;
import com.e.spectra.ui.menu.SettingsActivity;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;

public class ItemDetailsActivity extends AbstractCatalogueActivity<ItemDetailViewModel> {
    @BindView(R.id.item_detail_code)
    TextView textViewCode;
    @BindView(R.id.item_detail_price)
    TextView textViewPrice;
    @BindView(R.id.item_detail_description)
    TextView textViewDescription;
    @BindView(R.id.item_detail_image)
    ImageView imageView;
    ItemData item;

    ItemDetailViewModel viewModel;

    @Inject

    @Named("ItemDetailViewModel")
    ViewModelProvider.Factory factory;
    // Call<Map<String, String>> map;

    @Override
    public ItemDetailViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, factory).get(ItemDetailViewModel.class);
        return viewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ButterKnife.bind(this);
        hideSoftKeyboard();
        // map = viewModel.getCallMap();

        if (getIntent().hasExtra(ECatalogueConstants.ITEM) && (getIntent().hasExtra(ECatalogueConstants.ITEM))) {
            item = (ItemData) getIntent().getSerializableExtra(ECatalogueConstants.ITEM);
            textViewCode.setText(item.getCode());
            // setPrice();
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

    private void bind() {
        ActivityItemDetailsBinding itemDetailsBinding = DataBindingUtil.setContentView(this, R.layout.activity_item_details);
//        viewModel = ViewModelProviders.of(this).get(ItemDetailViewModel.class);
//        itemDetailsBinding.setViewModel(viewModel);

    }

    public void setPrice() {
        SharedPreferences shared = getSharedPreferences("currencyPrefs", MODE_PRIVATE);
        String convertedResponse;
        convertedResponse = shared.getString(SettingsActivity.PRICE_CONVERSION_NAME, "1");
        Double price = Double.valueOf(convertedResponse) * Double.valueOf(item.getPrice());
        textViewPrice.setText(price.toString());
//        map.enqueue(new Callback<Map<String, String>>() {
//            @Override
//            public void onResponse(Call<Map<String, String>> call, Response<Map<String, String>> response) {
//                if (response.isSuccessful()) {
//               //     = response.body().get(SettingsActivity.PRICE_CONVERSION_NAME);
//                    String convertedResponse ;
//                    convertedResponse=shared.getString(SettingsActivity.PRICE_CONVERSION_NAME,"1");
//                    Double price = Double.valueOf(convertedResponse) * Double.valueOf(item.getPrice());
//                    textViewPrice.setText(price.toString());
//                } else {
//                    // error response, no access to resource?
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Map<String, String>> call, Throwable t) {
//
//            }
//        });
    }
}
