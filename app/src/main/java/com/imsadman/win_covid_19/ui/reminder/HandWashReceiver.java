package com.imsadman.win_covid_19.ui.reminder;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.imsadman.win_covid_19.utils.Generics;

public class HandWashReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Generics.notificationManager(context, "notifyHandWash", "Hand Wash Reminder", "It's Time to Wash Your Hands", 1);

    }
}
