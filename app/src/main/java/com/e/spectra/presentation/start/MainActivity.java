package com.e.spectra.presentation.start;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModel;

import com.e.spectra.R;
import com.e.spectra.presentation.AbstractCatalogueActivity;
import com.e.spectra.presentation.fragments.MainBannerFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AbstractCatalogueActivity<ViewModel> {

    @BindView(R.id.btn_signUp)
    Button button_signUp;
    @BindView(R.id.btn_register)
    Button button_newUser;
    private SharedPreferences mPreferences;
    private String sharedPrefFile =
            "com.e.spectra";


    @Override
    public ViewModel getViewModel() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainpage);
        ButterKnife.bind(this);
        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        MainBannerFragment bannerFragment = new MainBannerFragment();
        transaction.add(R.id.mainbannerFragment, bannerFragment, " Main Banner Fragment");
        bannerFragment.getImage();
        transaction.commit();


    }


    @Override
    protected void onStart() {
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);

        super.onStart();
    }

    @OnClick(R.id.btn_register)
    public void newUser() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_signUp)
    public void signUp() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
