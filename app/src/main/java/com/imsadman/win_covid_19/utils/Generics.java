package com.imsadman.win_covid_19.utils;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.imsadman.win_covid_19.R;
import com.imsadman.win_covid_19.ui.reminder.AvoidTouchReceiver;

import static android.content.Context.ALARM_SERVICE;

public class Generics {
    private static final String TAG = "Generics";

    private static String PLACES_API_KEY = "AIzaSyBYPIXZInwFjimC27DfhrzYSDUUdNk1cAk";

    public static String getPlacesApiKey() {
        return PLACES_API_KEY;
    }

    public static void createNotificationChannel(Context context, String title, String text, String channelId) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = title;
            String description = text;
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(channelId, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }


    public static void notificationManager(Context context, String channelId, String title, String msg, int id) {

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, channelId)
                .setSmallIcon(R.drawable.ic_notifications_black_24dp)
                .setContentTitle(title)
                .setContentText(msg)
                .setPriority(NotificationCompat.PRIORITY_HIGH);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);

        notificationManagerCompat.notify(id, builder.build());
    }

    public static boolean setSharedPref(Context context, String name, String value) {
        SharedPreferences.Editor editor = context.getSharedPreferences("IS_ALARM_SET", 0).edit();
        editor.putString(name, value);
        editor.apply();
        return true;
    }

    public static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences("IS_ALARM_SET", 0);
    }

    public static void removeSharedPreferences(Context context, String key) {
        SharedPreferences preferences = context.getSharedPreferences("IS_ALARM_SET", 0);
        preferences.edit().remove(key).apply();
    }

    public static void removeAlarm(Context context, String channelId, int requestCode, String pref_name, String toastText) {
        AlarmManager alarmMgr = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        Intent intent = new Intent(context, AvoidTouchReceiver.class);
        intent.putExtra("channelId", channelId);
        PendingIntent alarmIntent = PendingIntent.getBroadcast(context, requestCode, intent, 0);
        alarmMgr.cancel(alarmIntent);
        Generics.setSharedPref(context, pref_name, "false");
        Toast.makeText(context, toastText, Toast.LENGTH_SHORT).show();
    }

    public static FirebaseFirestore initFirestore(Context context) {
        FirebaseApp.initializeApp(context);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        return db;
    }

    public static FirebaseUser getUser() {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        return currentUser;
    }
}
