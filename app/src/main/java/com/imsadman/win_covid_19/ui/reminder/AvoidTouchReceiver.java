package com.imsadman.win_covid_19.ui.reminder;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.imsadman.win_covid_19.utils.Generics;

public class AvoidTouchReceiver extends BroadcastReceiver {

    private static final String TAG = "AvoidTouchReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {

            Generics.notificationManager(context, "avoidTouching", "Avoid Face Touching", "Do Not Touch Your Face", 2);
    }
}
