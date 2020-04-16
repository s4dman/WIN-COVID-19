package com.imsadman.win_covid_19.ui.reminder;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.imsadman.win_covid_19.R;

public class AlertReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "notifyHandWash")
                .setSmallIcon(R.drawable.ic_notifications_black_24dp)
                .setContentTitle("Hand Wash Reminder")
                .setContentText("It's Time to Wash Your HANDS")
                .setPriority(NotificationCompat.PRIORITY_HIGH);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);

        notificationManagerCompat.notify(1, builder.build());
    }
}
