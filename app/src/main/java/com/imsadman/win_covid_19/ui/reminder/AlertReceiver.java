package com.imsadman.win_covid_19.ui.reminder;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.imsadman.win_covid_19.R;
import com.imsadman.win_covid_19.Utils.Generics;

public class AlertReceiver extends BroadcastReceiver {

    private static final String TAG = "AlertReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {

        String chanelId = intent.getExtras().getString("channelId");

        if (chanelId.equals("avoidTouching")) {

            Generics.notificationManager(context, chanelId, "Avoid Face Touching", "Do Not Touch Your Face", 2);

        } else if (chanelId.equals("notifyHandWash")) {

            Generics.notificationManager(context, chanelId, "Hand Wash Reminder", "It's Time to Wash Your Hands", 1);


        } else {
            Log.d(TAG, "onReceive: Nothing");
        }

    }
}
