package com.e.spectra.presentation;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.e.spectra.presentation.data.CategoryData;
import com.e.spectra.presentation.adapter.CategoriesAdapter;
import com.e.spectra.constants.ECatalogueConstants;
import com.e.spectra.R;
import com.e.spectra.data.firebase.FirebaseManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.e.spectra.constants.FirebaseManageConstants.CATEGORIES_ALL;
import static com.e.spectra.constants.FirebaseManageConstants.CATEGORY_BRAND;
import static com.e.spectra.constants.FirebaseManageConstants.CATEGORY_NAME;

public class CategoriesActivity extends AppCompatActivity implements OnCompleteListener<QuerySnapshot> {

    private CategoriesAdapter categoriesAdapter;
    @BindView(R.id.categories_view)
    RecyclerView recyclerCategoriesView;
    private RecyclerView.LayoutManager layoutManager;
    private String brandName;
    ArrayList<CategoryData> categoryList;
    @BindView(R.id.editText_category_search)
    EditText categorySearchEditText;

//    @Override
//    public ViewModel getViewModel() {
//        return null;
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ButterKnife.bind(this);
        if (getIntent().hasExtra(ECatalogueConstants.BRAND) && (getIntent().hasExtra(ECatalogueConstants.BRAND))) {
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
                categoryData.setName(FirebaseManager.readValue(snapshot, CATEGORY_NAME));
                if (brandName.equals(CATEGORIES_ALL)) {
                    categoryData.setBrand(brandName);
                } else {
                    categoryData.setBrand(FirebaseManager.readValue(snapshot, CATEGORY_BRAND));
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
        } else {
            Log.e("getCategories", task.getException().getMessage());
        }
    }

    public void getCategoriesView() {
        recyclerCategoriesView.setHasFixedSize(true);
        FirebaseManager.getInstance().getAllCategories(this, brandName);
      //  hideSoftKeyboard();
    }


//    @Override
//    public void hideSoftKeyboard() {
//        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
//    }

}
