package com.e.ecatalogue.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.e.ecatalogue.BrandsAdapter;
import com.e.ecatalogue.CategoriesAdapter;
import com.e.ecatalogue.ECatalogueConstants;
import com.e.ecatalogue.R;
import com.e.ecatalogue.data.BrandData;
import com.e.ecatalogue.data.CategoryData;
import com.e.ecatalogue.firebase.FirebaseManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import static com.e.ecatalogue.firebase.FirebaseManager.BRAND_IMAGEURL;
import static com.e.ecatalogue.firebase.FirebaseManager.BRAND_NAME;
import static com.e.ecatalogue.firebase.FirebaseManager.CATEGORY_BRAND;
import static com.e.ecatalogue.firebase.FirebaseManager.CATEGORY_NAME;
import static com.e.ecatalogue.firebase.FirebaseManager.readValue;

public class CategoriesActivity extends AppCompatActivity implements OnCompleteListener<QuerySnapshot> {

    private static RecyclerView.Adapter cateoriesAdapter;
    private static RecyclerView recyclerCategoriesView;
    private RecyclerView.LayoutManager layoutManager;
    private String brandName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        if (getIntent().hasExtra(ECatalogueConstants.BRAND) && getIntent().hasExtra(ECatalogueConstants.BRAND)) {
            brandName = getIntent().getStringExtra(ECatalogueConstants.BRAND);
            getCategoriesView();
        }
    }

    @Override
    public void onComplete(@NonNull Task<QuerySnapshot> task) {
        if (task.isSuccessful()) {
            ArrayList<CategoryData> categoryList = new ArrayList<>();
            for (QueryDocumentSnapshot snapshot : task.getResult()) {
                CategoryData categoryData = new CategoryData();
                categoryData.setName(readValue(snapshot, CATEGORY_NAME));
                categoryData.setBrand(readValue(snapshot,CATEGORY_BRAND));
                categoryList.add(categoryData);
            }
            layoutManager = new LinearLayoutManager(this);
            recyclerCategoriesView.setLayoutManager(layoutManager);
            recyclerCategoriesView.setItemAnimator(new DefaultItemAnimator());
            cateoriesAdapter = new CategoriesAdapter(categoryList,this);
            recyclerCategoriesView.setAdapter(cateoriesAdapter);
        }
    }

    public void getCategoriesView() {
        recyclerCategoriesView = (RecyclerView) findViewById(R.id.categories_view);
        recyclerCategoriesView.setHasFixedSize(true);
        FirebaseManager.getInstance().getAllCategories(this, brandName);
    }
}
