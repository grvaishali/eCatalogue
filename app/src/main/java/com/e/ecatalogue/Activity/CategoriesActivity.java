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
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.e.ecatalogue.CategoriesAdapter;
import com.e.ecatalogue.ContactUsActivityy;
import com.e.ecatalogue.ECatalogueConstants;
import com.e.ecatalogue.R;
import com.e.ecatalogue.data.CategoryData;
import com.e.ecatalogue.firebase.FirebaseManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static com.e.ecatalogue.firebase.FirebaseManager.CATEGORY_BRAND;
import static com.e.ecatalogue.firebase.FirebaseManager.CATEGORY_NAME;
import static com.e.ecatalogue.firebase.FirebaseManager.readValue;

public class CategoriesActivity extends AppCompatActivity implements OnCompleteListener<QuerySnapshot> {

    private static CategoriesAdapter categoriesAdapter;
    private static RecyclerView recyclerCategoriesView;
    private RecyclerView.LayoutManager layoutManager;
    private String brandName;
    ArrayList<CategoryData> categoryList;
    EditText categorySearchEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        categorySearchEditText = findViewById(R.id.editText_category_search);
        if (getIntent().hasExtra(ECatalogueConstants.BRAND) && getIntent().hasExtra(ECatalogueConstants.BRAND)) {
            brandName = getIntent().getStringExtra(ECatalogueConstants.BRAND);
            getCategoriesView();
        }
        categorySearchEditText.setEnabled(false);
        categorySearchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filterCategories(s.toString());
            }
        });
    }

    private void filterCategories(String searchString) {
        ArrayList<CategoryData> filteredLCategoryList = new ArrayList<>();

        for (CategoryData categoryData : categoryList) {
            if (categoryData.getName().toLowerCase().contains(searchString.toLowerCase())) {
                filteredLCategoryList.add(categoryData);
            }
        }
        categoriesAdapter.filterList(filteredLCategoryList);
    }


    @Override
    public void onComplete(@NonNull Task<QuerySnapshot> task) {
        if (task.isSuccessful()) {
            Set<CategoryData> categoriesSet = new HashSet();
            for (QueryDocumentSnapshot snapshot : task.getResult()) {
                CategoryData categoryData = new CategoryData();
                categoryData.setName(readValue(snapshot, CATEGORY_NAME));
                if (brandName.equals(FirebaseManager.CATEGORIES_ALL)) {
                    categoryData.setBrand(brandName);
                } else {
                    categoryData.setBrand(readValue(snapshot, CATEGORY_BRAND));
                }
                categoriesSet.add(categoryData);
            }
            categoryList = new ArrayList<>(categoriesSet);
            layoutManager = new LinearLayoutManager(this);
            recyclerCategoriesView.setLayoutManager(layoutManager);
            recyclerCategoriesView.setItemAnimator(new DefaultItemAnimator());
            categoriesAdapter = new CategoriesAdapter(categoryList, this);
            recyclerCategoriesView.setAdapter(categoriesAdapter);
            categorySearchEditText.setEnabled(true);
        }else {
            Log.e("getCategories", task.getException().getMessage());
        }
    }

    public void getCategoriesView() {
        recyclerCategoriesView = (RecyclerView) findViewById(R.id.categories_view);
        recyclerCategoriesView.setHasFixedSize(true);
        FirebaseManager.getInstance().getAllCategories(this, brandName);
        hideSoftKeyboard();
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
//                startActivity(new Intent(CategoriesActivity.this,BrandsActivity.class));
//                return true;

            case R.id.exit:
                finish();
                return true;

            case R.id.home:
                Intent homeIntent = new Intent(CategoriesActivity.this, BrandsActivity.class);
                startActivity(homeIntent);
                return true;

            case R.id.info:
                startActivity(new Intent(CategoriesActivity.this,AboutUsActivity.class));

                return true;



            case R.id.termsCondition:

                startActivity(new Intent(CategoriesActivity.this,TermsConditionActivity.class));
                return true;


            case R.id.contactUs:
                startActivity(new Intent(CategoriesActivity.this, ContactUsActivityy.class));
                return true;



            case R.id.culture:
                startActivity(new Intent(CategoriesActivity.this, CultureActivity.class));
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    private void hideSoftKeyboard() {
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

}
