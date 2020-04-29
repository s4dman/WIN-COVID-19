package com.imsadman.win_covid_19.ui.reminder;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.imsadman.win_covid_19.utils.Generics;

public class CleanHouseReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Generics.notificationManager(context, "cleanHouse", "House Clean Reminder", "It's Time to Clean Your House", 3);
    }
}
