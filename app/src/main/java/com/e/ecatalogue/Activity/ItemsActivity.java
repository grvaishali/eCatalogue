package com.e.ecatalogue.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.e.ecatalogue.ContactUsActivityy;
import com.e.ecatalogue.ECatalogueConstants;
import com.e.ecatalogue.ItemsAdapter;
import com.e.ecatalogue.R;
import com.e.ecatalogue.data.ItemData;
import com.e.ecatalogue.firebase.FirebaseManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import static com.e.ecatalogue.firebase.FirebaseManager.ITEM_BRAND;
import static com.e.ecatalogue.firebase.FirebaseManager.ITEM_CATEGORY;
import static com.e.ecatalogue.firebase.FirebaseManager.ITEM_CODE;
import static com.e.ecatalogue.firebase.FirebaseManager.ITEM_DESCRIPTION;
import static com.e.ecatalogue.firebase.FirebaseManager.ITEM_IMAGEURL;
import static com.e.ecatalogue.firebase.FirebaseManager.ITEM_NAME;
import static com.e.ecatalogue.firebase.FirebaseManager.ITEM_PRICE;
import static com.e.ecatalogue.firebase.FirebaseManager.readValue;

public class ItemsActivity extends AbstractCatalogueActivity implements OnCompleteListener<QuerySnapshot> {

    private static ItemsAdapter itemsAdapter;
    private static RecyclerView recyclerItemsView;
    private RecyclerView.LayoutManager layoutManager;
    EditText itemSearchEditText;
    private String brandName;
    private String categoryName;
    ArrayList<ItemData> itemsList;
    FragmentManager manager;
    FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        itemSearchEditText = findViewById(R.id.editText_items_search);
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
                itemData.setCode(readValue(snapshot, ITEM_CODE));
                itemData.setBrand(readValue(snapshot, ITEM_BRAND));
                itemData.setCategory(readValue(snapshot, ITEM_CATEGORY));
                itemData.setDescription(readValue(snapshot, ITEM_DESCRIPTION));
                itemData.setName(readValue(snapshot, ITEM_NAME));
                itemData.setPrice(readValue(snapshot, ITEM_PRICE));
                itemData.setImageUrl(readValue(snapshot, ITEM_IMAGEURL));
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
        recyclerItemsView = (RecyclerView) findViewById(R.id.items_view);
        recyclerItemsView.setHasFixedSize(true);
        FirebaseManager.getInstance().getAllItems(this, categoryName, brandName);
        hideSoftKeyboard();
    }

    private void filterItems(String searchString) {
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


}
