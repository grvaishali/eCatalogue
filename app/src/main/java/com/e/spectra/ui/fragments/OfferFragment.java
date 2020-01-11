package com.e.spectra.ui.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.e.spectra.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


import butterknife.BindView;
import butterknife.ButterKnife;

import static com.e.spectra.constants.FirebaseManageConstants.MASTER_MAIN_PROMOTION_TEXT;
import static com.e.spectra.firebase.FirebaseManager.getInstance;
import static com.e.spectra.firebase.FirebaseManager.readValue;

public class OfferFragment extends Fragment implements OnCompleteListener<QuerySnapshot> {

    @BindView(R.id.offer_textView)
    TextView offerTextView;

    public OfferFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_offer, container, false);
        ButterKnife.bind(this,view);
        TranslateAnimation animation = new TranslateAnimation(0.0f, 700.0f, 0.0f, 0.0f);
        offerTextView.setTextColor(Color.RED);
        offerTextView.setTextSize(20);
        animation.setDuration(7000); // animation duration
        animation.setRepeatCount(-1); // animation repeat count
        animation.setRepeatMode(0); // repeat animation (left to right, right to left)
        animation.setFillAfter(true);
        offerTextView.startAnimation(animation);
        //offerTextView.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.move));
        getOffer();
        return view;
    }

    @Override
    public void onComplete(@NonNull Task<QuerySnapshot> task) {
        if (task.isSuccessful()) {
            for (QueryDocumentSnapshot snapshot : task.getResult()) {
                offerTextView.setText(readValue(snapshot, MASTER_MAIN_PROMOTION_TEXT));
            }
        } else {
            Log.e("Offer", "Offer Loadig Error");
        }
    }

    public void getOffer() {
        getInstance().getBannerImages(this);
    }


}
