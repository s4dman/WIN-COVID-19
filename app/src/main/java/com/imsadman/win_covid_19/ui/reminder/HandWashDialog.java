package com.imsadman.win_covid_19.ui.reminder;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.imsadman.win_covid_19.R;
import com.imsadman.win_covid_19.Utils.Generics;

import java.util.Calendar;

import static android.content.Context.ALARM_SERVICE;

public class HandWashDialog extends BottomSheetDialogFragment {

    private static final String TAG = "HandWashDialog";

    private RadioButton mRadioBtn1, mBtnRadio2;
    private Button mBtnSetReminder;
    private AlarmManager alarmMgr;
    private PendingIntent alarmIntent;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottomsheet_handwash, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Generics.createNotificationChannel(getContext(), "Stay Safe", "Wash your hands regularly", "notifyHandWash");

        initViews(view);
    }

    private void initViews(View view) {
        mRadioBtn1 = view.findViewById(R.id.radioButton1);
        mBtnRadio2 = view.findViewById(R.id.radioButton2);
        mBtnSetReminder = view.findViewById(R.id.btn_set_reminder);

        mBtnSetReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mRadioBtn1.isChecked()) {
                    handWashReminder(1);
                    dismiss();
                } else {
                    handWashReminder(2);
                    dismiss();
                }
            }
        });
    }

    private void handWashReminder(int hour) {

        alarmMgr = (AlarmManager) getContext().getSystemService(ALARM_SERVICE);
        Intent intent = new Intent(getContext(), AlertReceiver.class);
        intent.putExtra("channelId", "notifyHandWash");
        alarmIntent = PendingIntent.getBroadcast(getContext(), 1, intent, 0);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        if (hour == 1) {
            calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR) + 1);
        } else {
            calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR) + 2);
        }
        calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, calendar.get(Calendar.SECOND));


        if (hour == 1) {
            alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_HOUR, alarmIntent);
            Generics.setSharedPref(getContext(),"handWashSet", "true");
            Toast.makeText(getContext(), "We will remind you every 1 hour", Toast.LENGTH_SHORT).show();
        } else {
            alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 7200000, alarmIntent);
            Generics.setSharedPref(getContext(),"handWashSet", "true");
            Toast.makeText(getContext(), "We will remind you every 2 hours", Toast.LENGTH_SHORT).show();
        }
    }
}
