package com.e.spectra.presentation.home;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.e.spectra.R;
import com.e.spectra.data.firebase.FirebaseManager;
import com.e.spectra.presentation.data.BrandData;
import com.e.spectra.presentation.fragments.OfferFragment;
import com.e.spectra.presentation.adapter.BrandsAdapter;
import com.e.spectra.util.ViewUtil;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.e.spectra.constants.FirebaseManageConstants.BRAND_IMAGEURL;
import static com.e.spectra.constants.FirebaseManageConstants.BRAND_NAME;

public class BrandsActivity extends AppCompatActivity implements OnCompleteListener<QuerySnapshot> {
    private static RecyclerView.Adapter brandsAdapter;
    private LinearLayoutManager layoutManager;

    @BindView(R.id.brands_view)
    RecyclerView recyclerBrandsView;
    FragmentManager manager;
    FragmentTransaction transaction;
    TextView view;
    private Boolean isScrolling;
    int currentItems, totalItems, scrollOutItems;
    ArrayList<BrandData> brandsList;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    QuerySnapshot Actualtask, Initialtask;


    @NonNull
    Task<QuerySnapshot> task;
    int i = 1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brands);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ButterKnife.bind(this);
        isScrolling = false;
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();

        // MainBannerFragment bannerFragment = new MainBannerFragment();
        OfferFragment offerFragment = new OfferFragment();
        // transaction.add(R.id.bannerFragment, bannerFragment, "Banner Fragment");
        transaction.add(R.id.offerFragment, offerFragment, "Offer Fragment");
        //bannerFragment.getImage();
        offerFragment.getOffer();
        transaction.commit();
        recyclerBrandsView = (RecyclerView) findViewById(R.id.brands_view);
        recyclerBrandsView.setHasFixedSize(true);
        getBrandsView();

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
            recyclerBrandsView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
                    if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                        isScrolling = true;
                    }
                }

                @Override
                public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    currentItems = layoutManager.getChildCount();
                    totalItems = layoutManager.getItemCount();
                    scrollOutItems = layoutManager.findFirstVisibleItemPosition();

                    if (isScrolling && (currentItems + scrollOutItems == totalItems)) {
                        isScrolling = false;

                        //  fetchData();
                    }
                }
            });
        } else {
            Log.e("getBrands", task.getException().getMessage());
        }
    }


    private void fetchData() {
        ViewUtil.showProgressBar(progressBar);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                DocumentSnapshot lastVisible = Actualtask.getDocuments()
                        .get(Actualtask.size() - 1);


                FirebaseManager.getInstance().getNext(lastVisible, 20);


                //  for (QueryDocumentSnapshot snapshot : Actualtask) {

//                    BrandData brandData = new BrandData();
//                    brandData.setName(FirebaseManager.readValue(snapshot, FirebaseManager.BRAND_NAME));
//                    brandData.setImageUrl(FirebaseManager.readValue(snapshot, FirebaseManager.BRAND_IMAGEURL));
//                    brandsList.add(brandData);
//                    brandsAdapter.notifyDataSetChanged();
//
////                    if (Actualtask.equals(10 * i)) {
////                        i++;
////                        progressBar.setVisibility(View.GONE);
////                        break;
////
////                    }
                //    }
                ViewUtil.hideProgressBar(progressBar);
            }
        }, 5000);

    }


    public void getBrandsView() {

        FirebaseManager.getInstance().getAllBrands(this);
       // hideSoftKeyboard();

    }


}

