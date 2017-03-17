package com.example.jordanlefevre.helloworld;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

/**
 * Created by jordanlefevre on 15/03/2017.
 */

public class PreferencesFragment extends PreferenceFragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);

        findPreference("notif_pref").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                NotificationManager notificationManager = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);

                Intent resultIntent = new Intent(getActivity(), MainActivity.class);

                PendingIntent application =
                        PendingIntent.getActivity(
                                getActivity(),
                                0,
                                resultIntent,
                                PendingIntent.FLAG_UPDATE_CURRENT
                        );
                NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getActivity())
                    .setSmallIcon(R.drawable.comment)
                    .setContentIntent(application)
                    .setContentTitle("Une notification !")
                    .setContentText("Bonjour !");

                notificationManager.notify((int) System.currentTimeMillis(), mBuilder.build());

                return true;
            }
        });
    }
}
