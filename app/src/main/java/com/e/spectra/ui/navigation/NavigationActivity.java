package com.e.spectra.ui.navigation;

import android.content.Intent;
import android.os.Bundle;

import com.e.spectra.R;
import com.e.spectra.firebase.FirebaseManager;
import com.e.spectra.ui.adapter.BrandsAdapter;
import com.e.spectra.ui.data.BrandData;
import com.e.spectra.ui.home.BrandsActivity;
import com.e.spectra.ui.menu.AboutUsActivity;
import com.e.spectra.ui.menu.AddBrandActivity;
import com.e.spectra.ui.menu.ContactUsActivity;
import com.e.spectra.ui.menu.SettingsActivity;
import com.e.spectra.ui.menu.TermsConditionActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.e.spectra.constants.FirebaseManageConstants.BRAND_IMAGEURL;
import static com.e.spectra.constants.FirebaseManageConstants.BRAND_NAME;

public class NavigationActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, OnCompleteListener<QuerySnapshot> {

    private AppBarConfiguration mAppBarConfiguration;
    @BindView(R.id.brandNames)
    RecyclerView recyclerBrandsView;
    ArrayList<BrandData> brandsList;
    QuerySnapshot Actualtask, Initialtask;
    private LinearLayoutManager layoutManager;
    private static RecyclerView.Adapter brandsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = findViewById(R.id.toolbar);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        recyclerBrandsView.setHasFixedSize(true);
        getBrandsView();
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NavigationActivity.this, AddBrandActivity.class);
                startActivity(intent);
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_share, R.id.nav_send)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public void onComplete(@NonNull Task<QuerySnapshot> task) {
        if (task.isSuccessful()) {
            brandsList = new ArrayList<>();
            Actualtask = task.getResult();


            for (QueryDocumentSnapshot snapshot : Actualtask) {
                BrandData brandData = new BrandData();
                brandData.setName(FirebaseManager.readValue(snapshot, BRAND_NAME));
//                if (FirebaseManager.readValue(snapshot, FirebaseManager.BRAND_NAME).equalsIgnoreCase("Swaraz"))
//                {
//                    isScrolling=false;
//                    recyclerBrandsView.clearOnScrollListeners();
//                   // recyclerBrandsView.stopScroll();
//
//
//                }
                brandData.setImageUrl(FirebaseManager.readValue(snapshot, BRAND_IMAGEURL));
                brandsList.add(brandData);
                // brandsAdapter.notifyDataSetChanged();
//                if (task.getResult().equals(10 * i)) {
//                    i++;
//                    break;
//                }
            }
            layoutManager = new LinearLayoutManager(this);
            recyclerBrandsView.setLayoutManager(layoutManager);
            recyclerBrandsView.setItemAnimator(new DefaultItemAnimator());
            brandsAdapter = new BrandsAdapter(brandsList, this);
            recyclerBrandsView.setAdapter(brandsAdapter);
        }
    }

    public void getBrandsView() {

        FirebaseManager.getInstance().getAllBrands(this);


    }

    @Override
    protected void onResume() {
        super.onResume();
        //  recyclerBrandsView.getAdapter().notifyDataSetChanged();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case R.id.nav_home:
                startActivity(new Intent(getApplicationContext(), BrandsActivity.class));
                return true;

            case R.id.nav_items:
                startActivity(new Intent(getApplicationContext(), BrandsActivity.class));
                return true;


            case R.id.nav_info:
                startActivity(new Intent(getApplicationContext(), AboutUsActivity.class));

                return true;


            case R.id.nav_contactUs:
                startActivity(new Intent(getApplicationContext(), ContactUsActivity.class));
                return true;


            case R.id.nav_tools:
                startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
                return true;

            default:
                return true;


        }
    }
}
