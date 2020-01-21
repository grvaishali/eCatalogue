package com.e.spectra.presentation.menu;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.DropDownPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SwitchPreferenceCompat;

import com.e.spectra.R;
import com.e.spectra.application.EcatalogueApplication;
import com.e.spectra.util.LocaleHelper;

import java.util.Map;


public class SettingsActivity extends AppCompatActivity {

    public static String
            PRICE_CONVERSION_NAME = "INR_USD";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.settings, new SettingsFragment())
                .commit();
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    public static class SettingsFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {
        public DropDownPreference currencyPreference;
        SharedPreferences currencyPrefs;

        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);
            currencyPreference = findPreference("currency_dropDown");
            currencyListener();
            SwitchPreferenceCompat preference = findPreference("Language");
            preference.setOnPreferenceChangeListener(new androidx.preference.Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    SwitchPreferenceCompat switchPreferenceCompat = (SwitchPreferenceCompat) preference;
                    if (switchPreferenceCompat.isChecked()) {
                        LocaleHelper.setLocale(getContext(), "en");
                    } else {
                        LocaleHelper.setLocale(getContext(), "fr");
                    }
                    getActivity().recreate();
                    return true;
                }

            });
        }

        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

        }

        public void currencyListener() {
            String value = currencyPreference.getValue();
            Map map = EcatalogueApplication.setCurrencyPrice();
            currencyPrefs = PreferenceManager.getDefaultSharedPreferences(getContext());

            switch (value) {
                case "US":

                    currencyPreference.setValue((map.get("USD_INR")).toString());

                    break;
                case "INR":
                    currencyPreference.setValue((map.get("INR_USD")).toString());


                    break;
                case "Ca":
                    currencyPreference.setValue((map.get("USD_INR")).toString());
//                    //  editor.putString("Ca", "USD_INR");
//                    PRICE_CONVERSION_NAME = "USD_INR";
                    break;


                default:
                    break;
            }
         //   currencyPrefs = (SharedPreferences) currencyPreference;
            currencyPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    DropDownPreference currencyPreference = (DropDownPreference) preference;
                    switch (value) {
                        case "US":
                            PRICE_CONVERSION_NAME = (String) map.get("USD_INR");

                            break;
                        case "INR":

                            PRICE_CONVERSION_NAME =  (String)map.get("INR_USD");

                            break;
                        case "Ca":

                            PRICE_CONVERSION_NAME = (String)map.get("USD_INR");
                            break;


                        default:
                            break;
                    }

                     getActivity().recreate();

                    return true;

                }
            });
        }

    }


}