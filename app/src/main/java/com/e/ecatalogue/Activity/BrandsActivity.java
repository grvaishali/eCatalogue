package com.e.ecatalogue.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.e.ecatalogue.BrandsAdapter;
import com.e.ecatalogue.Fragment.MainBannerFragment;
import com.e.ecatalogue.Fragment.OfferFragment;
import com.e.ecatalogue.data.BrandData;
import com.e.ecatalogue.R;
import com.e.ecatalogue.firebase.FirebaseManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import static com.e.ecatalogue.firebase.FirebaseManager.*;

public class BrandsActivity extends AppCompatActivity implements OnCompleteListener<QuerySnapshot> {
    private static RecyclerView.Adapter brandsAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerBrandsView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brands);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        MainBannerFragment bannerFragment = new MainBannerFragment();
        OfferFragment offerFragment = new OfferFragment();
        transaction.add(R.id.bannerFragment, bannerFragment, "Banner Fragment");
        transaction.add(R.id.offerFragment,offerFragment,"Offer Fragment");
        bannerFragment.getImage();
        offerFragment.getOffer();
        transaction.commit();
        getBrandsView();
    }

    @Override
    public void onComplete(@NonNull Task<QuerySnapshot> task) {
        if (task.isSuccessful()) {
            ArrayList<BrandData> brandsList = new ArrayList<>();
            for (QueryDocumentSnapshot snapshot : task.getResult()) {
                BrandData brandData = new BrandData();
                brandData.setName(readValue(snapshot, BRAND_NAME));
                brandData.setImageUrl(readValue(snapshot, BRAND_IMAGEURL));
                brandsList.add(brandData);
            }
            layoutManager = new LinearLayoutManager(this);
            recyclerBrandsView.setLayoutManager(layoutManager);
            recyclerBrandsView.setItemAnimator(new DefaultItemAnimator());
            brandsAdapter = new BrandsAdapter(brandsList, this);
            recyclerBrandsView.setAdapter(brandsAdapter);
        }
    }


    public void getBrandsView() {
        recyclerBrandsView = (RecyclerView) findViewById(R.id.brands_view);
        recyclerBrandsView.setHasFixedSize(true);
        FirebaseManager.getInstance().getAllBrands(this);
    }
}

