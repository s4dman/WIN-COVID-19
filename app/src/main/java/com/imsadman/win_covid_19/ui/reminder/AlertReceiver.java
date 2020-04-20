package com.imsadman.win_covid_19.ui.reminder;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.imsadman.win_covid_19.utils.Generics;

public class AlertReceiver extends BroadcastReceiver {

    private static final String TAG = "AlertReceiver";

    /*TODO: For repeatingAlarm only 1 BroadcastReceiver should launch*/

    @Override
    public void onReceive(Context context, Intent intent) {

        String chanelId = intent.getExtras().getString("channelId");

        if (chanelId.equals("notifyHandWash")) {

            Generics.notificationManager(context, chanelId, "Hand Wash Reminder", "It's Time to Wash Your Hands", 1);

        } else if (chanelId.equals("avoidTouching")) {

            Generics.notificationManager(context, chanelId, "Avoid Face Touching", "Do Not Touch Your Face", 2);

        } else {
            Generics.notificationManager(context, chanelId, "House Clean Reminder", "It's Time to Clean Your House", 3);
        }

    }
}
