package com.example.jordanlefevre.helloworld;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by jordanlefevre on 15/03/2017.
 */

public class Preferences extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preferences_activity);

        getFragmentManager().beginTransaction()
                .replace(R.id.preferencesFragment, new PreferencesFragment())
                .commitAllowingStateLoss();
    }
}
