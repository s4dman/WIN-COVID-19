package com.imsadman.win_covid_19.Utils;

import android.content.Context;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.imsadman.win_covid_19.R;

public class Generics {
    private static final String TAG = "Generics";

   public static void notificationManager (Context context, String channelId, String title, String msg, int id) {

       NotificationCompat.Builder builder = new NotificationCompat.Builder(context, channelId)
               .setSmallIcon(R.drawable.ic_notifications_black_24dp)
               .setContentTitle(title)
               .setContentText(msg)
               .setPriority(NotificationCompat.PRIORITY_HIGH);

       NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);

       notificationManagerCompat.notify(id, builder.build());
   }
}
