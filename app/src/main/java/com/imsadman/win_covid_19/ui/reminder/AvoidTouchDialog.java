package com.imsadman.win_covid_19.ui.reminder;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.imsadman.win_covid_19.R;
import com.imsadman.win_covid_19.utils.Generics;

import java.util.Calendar;

public class AvoidTouchDialog extends BottomSheetDialogFragment {
    private static final String TAG = "AvoidTouchDialog";

    private RadioButton mRadioBtn1;
    private Button mBtnSetReminder;

    private AlarmManager alarmMgr;
    private PendingIntent alarmIntent;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottomsheet_avoidtouch, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Generics.createNotificationChannel(getContext(), "Avoid Face Touching", "Avoid touching your face", "avoidTouching");

        initViews(view);
    }

    private void initViews(View view) {
        mRadioBtn1 = view.findViewById(R.id.avoid_radioButton1);
        mBtnSetReminder = view.findViewById(R.id.btn_avoid_touch_reminder);

        mBtnSetReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mRadioBtn1.isChecked()) {
                    avoidTouchReminder(1);
                    dismiss();
                } else {
                    avoidTouchReminder(2);
                    dismiss();
                }
            }
        });
    }

    private void avoidTouchReminder(int hour) {

        alarmMgr = (AlarmManager) getContext().getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(getContext(), AlertReceiver.class);
        intent.putExtra("channelId", "avoidTouching");
        alarmIntent = PendingIntent.getBroadcast(getContext(), 2, intent, 0);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR));
        calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE));
        if (hour == 1) {
            calendar.set(Calendar.SECOND, calendar.get(Calendar.SECOND) + 15);
        } else {
            calendar.set(Calendar.SECOND, calendar.get(Calendar.SECOND) + 20);
        }

        if (hour == 1) {
            alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_HOUR, alarmIntent);
            Generics.setSharedPref(getContext(),"pref_avoidTouch", "true");
            Toast.makeText(getContext(), "We will remind you every 1 hour", Toast.LENGTH_SHORT).show();
        } else {
            alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 7200000, alarmIntent);
            Generics.setSharedPref(getContext(),"pref_avoidTouch", "true");
            Toast.makeText(getContext(), "We will remind you every 2 hours", Toast.LENGTH_SHORT).show();
        }

    }
}
