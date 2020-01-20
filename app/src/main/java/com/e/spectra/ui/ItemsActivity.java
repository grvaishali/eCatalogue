package com.e.spectra.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.e.spectra.R;
import com.e.spectra.constants.ECatalogueConstants;
import com.e.spectra.databinding.ActivityItemDetailsBinding;
import com.e.spectra.databinding.ActivityItemsBindingImpl;
import com.e.spectra.firebase.FirebaseManager;
import com.e.spectra.model.ItemDetailViewModel;
import com.e.spectra.model.ItemViewModel;
import com.e.spectra.ui.adapter.ItemsAdapter;
import com.e.spectra.ui.data.ItemData;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Named;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.e.spectra.constants.FirebaseManageConstants.ITEM_BRAND;
import static com.e.spectra.constants.FirebaseManageConstants.ITEM_CATEGORY;
import static com.e.spectra.constants.FirebaseManageConstants.ITEM_CODE;
import static com.e.spectra.constants.FirebaseManageConstants.ITEM_DESCRIPTION;
import static com.e.spectra.constants.FirebaseManageConstants.ITEM_IMAGEURL;
import static com.e.spectra.constants.FirebaseManageConstants.ITEM_NAME;
import static com.e.spectra.constants.FirebaseManageConstants.ITEM_PRICE;

public class ItemsActivity extends AbstractCatalogueActivity<ItemViewModel> implements OnCompleteListener<QuerySnapshot> {

    private static ItemsAdapter itemsAdapter;
    @BindView(R.id.items_view)
    RecyclerView recyclerItemsView;
    private RecyclerView.LayoutManager layoutManager;
    @BindView(R.id.editText_items_search)
    EditText itemSearchEditText;
    private String brandName;
    private String categoryName;
    ArrayList<ItemData> itemsList;
    FragmentManager manager;
    FragmentTransaction transaction;

    @Inject
    ItemViewModel viewModel;

    @Inject
    @Named("ItemViewModel")
    ViewModelProvider.Factory factory;

    @Override
    public ItemViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, factory).get(ItemViewModel.class);
        return viewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind();
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        if (getIntent().hasExtra(ECatalogueConstants.BRAND) && getIntent().hasExtra(ECatalogueConstants.BRAND)) {
            brandName = getIntent().getStringExtra(ECatalogueConstants.BRAND);
            categoryName = getIntent().getStringExtra(ECatalogueConstants.CATEGORY);
            getItemsView();
        }

        itemSearchEditText.setEnabled(false);
        itemSearchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                filterItems(s.toString());
            }
        });
    }

    @Override
    public void onComplete(@NonNull Task<QuerySnapshot> task) {
        if (task.isSuccessful()) {
            itemsList = new ArrayList<>();
            for (QueryDocumentSnapshot snapshot : task.getResult()) {
                ItemData itemData = new ItemData();
                itemData.setCode(FirebaseManager.readValue(snapshot, ITEM_CODE));
                itemData.setBrand(FirebaseManager.readValue(snapshot, ITEM_BRAND));
                itemData.setCategory(FirebaseManager.readValue(snapshot, ITEM_CATEGORY));
                itemData.setDescription(FirebaseManager.readValue(snapshot, ITEM_DESCRIPTION));
                itemData.setName(FirebaseManager.readValue(snapshot, ITEM_NAME));
                itemData.setPrice(FirebaseManager.readValue(snapshot, ITEM_PRICE));
                itemData.setImageUrl(FirebaseManager.readValue(snapshot, ITEM_IMAGEURL));
                itemsList.add(itemData);
            }
            layoutManager = new LinearLayoutManager(this);
            recyclerItemsView.setLayoutManager(layoutManager);
            recyclerItemsView.setItemAnimator(new DefaultItemAnimator());
            itemsAdapter = new ItemsAdapter(itemsList, this);
            recyclerItemsView.setAdapter(itemsAdapter);
            itemSearchEditText.setEnabled(true);
        } else {
            Log.e("getItems", task.getException().getMessage());
        }
    }

    public void getItemsView() {
        recyclerItemsView.setHasFixedSize(true);
        FirebaseManager.getInstance().getAllItems(this, categoryName, brandName);
        //  hideSoftKeyboard();
    }

    public void filterItems(String searchString) {
        ArrayList<ItemData> filteredItemsList = new ArrayList<>();
        for (ItemData itemData : itemsList) {
            if (itemData.getName().toLowerCase().contains(searchString.toLowerCase())
                    || itemData.getCode().toLowerCase().contains(searchString.toLowerCase())) {
                filteredItemsList.add(itemData);
            }
        }
        itemsAdapter.filterList(filteredItemsList);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, CategoriesActivity.class);
        intent.putExtra(ECatalogueConstants.BRAND, brandName);
        this.startActivity(intent);
    }

    private void bind() {
        ActivityItemsBindingImpl itemDetailsBinding = DataBindingUtil.setContentView(this, R.layout.activity_items);
        // viewModel = ViewModelProviders.of(this).get(ItemDetailViewModel.class);
        itemDetailsBinding.setViewModel(viewModel);

    }

}
