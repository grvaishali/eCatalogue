package com.e.spectra.ui.menu;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SwitchPreferenceCompat;

import com.e.spectra.R;
import com.e.spectra.ui.AbstractCatalogueActivity;
import com.e.spectra.util.LocaleHelper;


public class SettingsActivity extends AbstractCatalogueActivity {
    public static final String
            KEY_PREF_EXAMPLE_SWITCH = "example_switch";

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
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);
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

    }


}