package com.e.spectra.ui.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.e.spectra.R;
import com.e.spectra.ui.data.BannerData;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


import butterknife.BindView;
import butterknife.ButterKnife;

import static com.e.spectra.constants.FirebaseManageConstants.MASTER_LOGO_IMAGE_URL;
import static com.e.spectra.constants.FirebaseManageConstants.MASTER_MAIN_BANNER_URL;
import static com.e.spectra.firebase.FirebaseManager.getInstance;
import static com.e.spectra.firebase.FirebaseManager.readValue;

public class MainBannerFragment extends Fragment implements OnCompleteListener<QuerySnapshot> {
    @BindView(R.id.fragment_main_banner_imageView_banner)
    ImageView bannerImage;

    @BindView(R.id.fragment_main_banner_imageView_logo)
    ImageView logoImage;

    BannerData bannerData;

    public MainBannerFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getImage();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_banner, container, false);
        ButterKnife.bind(this, view);
        getImage();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onComplete(@NonNull Task<QuerySnapshot> task) {
        if (task.isSuccessful()) {
            bannerData = new BannerData();
            for (QueryDocumentSnapshot snapshot : task.getResult()) {
                bannerData.setMainImageUrl(readValue(snapshot, MASTER_MAIN_BANNER_URL));
                bannerData.setLogoImageUrl(readValue(snapshot, MASTER_LOGO_IMAGE_URL));
            }
        } else {
            Log.e("Main Banner", "Main Immage Loadig Error");
        }
        Glide.with(bannerImage.getContext()).load(bannerData.getMainImageUrl()).centerCrop().into(bannerImage);
        Glide.with(logoImage.getContext()).load(bannerData.getLogoImageUrl()).centerCrop().into(logoImage);
    }

    public void getImage() {
        getInstance().getBannerImages(this);
    }

}
