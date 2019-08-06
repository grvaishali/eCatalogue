package com.e.ecatalogue.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.e.ecatalogue.CategoriesAdapter;
import com.e.ecatalogue.ECatalogueConstants;
import com.e.ecatalogue.ItemsAdapter;
import com.e.ecatalogue.R;
import com.e.ecatalogue.data.CategoryData;
import com.e.ecatalogue.data.ItemData;
import com.e.ecatalogue.firebase.FirebaseManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import static com.e.ecatalogue.firebase.FirebaseManager.CATEGORY_NAME;
import static com.e.ecatalogue.firebase.FirebaseManager.ITEM_BRAND;
import static com.e.ecatalogue.firebase.FirebaseManager.ITEM_CATEGORY;
import static com.e.ecatalogue.firebase.FirebaseManager.ITEM_CODE;
import static com.e.ecatalogue.firebase.FirebaseManager.ITEM_DESCRIPTION;
import static com.e.ecatalogue.firebase.FirebaseManager.ITEM_IMAGEURL;
import static com.e.ecatalogue.firebase.FirebaseManager.ITEM_PRICE;
import static com.e.ecatalogue.firebase.FirebaseManager.readValue;

public class ItemsActivity extends AppCompatActivity implements OnCompleteListener<QuerySnapshot> {

    private static RecyclerView.Adapter itemsAdapter;
    private static RecyclerView recyclerItemsView;
    private RecyclerView.LayoutManager layoutManager;
    private String brandName;
    private String categoryName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);
        if (getIntent().hasExtra(ECatalogueConstants.BRAND) && getIntent().hasExtra(ECatalogueConstants.BRAND)) {
            brandName = getIntent().getStringExtra(ECatalogueConstants.BRAND);
            categoryName = getIntent().getStringExtra(ECatalogueConstants.CATEGORY);
            getItemsView();
        }
    }

    @Override
    public void onComplete(@NonNull Task<QuerySnapshot> task) {
        if (task.isSuccessful()) {
            ArrayList<ItemData> itemList = new ArrayList<>();
            for (QueryDocumentSnapshot snapshot : task.getResult()) {
                ItemData itemData = new ItemData();
                itemData.setCode(readValue(snapshot, ITEM_CODE));
                itemData.setBrand(readValue(snapshot, ITEM_BRAND));
                itemData.setCategory(readValue(snapshot, ITEM_CATEGORY));
                itemData.setDescription(readValue(snapshot, ITEM_DESCRIPTION));
                itemData.setPrice(readValue(snapshot, ITEM_PRICE));
                itemData.setImageUrl(readValue(snapshot, ITEM_IMAGEURL));
                itemList.add(itemData);
            }
            layoutManager = new LinearLayoutManager(this);
            recyclerItemsView.setLayoutManager(layoutManager);
            recyclerItemsView.setItemAnimator(new DefaultItemAnimator());
            itemsAdapter = new ItemsAdapter(itemList,this);
            recyclerItemsView.setAdapter(itemsAdapter);
        }
    }

    public void getItemsView() {
        recyclerItemsView = (RecyclerView) findViewById(R.id.items_view);
        recyclerItemsView.setHasFixedSize(true);
        FirebaseManager.getInstance().getAllItems(this, categoryName, brandName);
    }

}
