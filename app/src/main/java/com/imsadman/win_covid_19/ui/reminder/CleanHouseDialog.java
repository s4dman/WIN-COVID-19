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
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.imsadman.win_covid_19.R;
import com.imsadman.win_covid_19.utils.Generics;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class CleanHouseDialog extends BottomSheetDialogFragment {
    private static final String TAG = "CleanHouseDialog";

    private EditText mDayEditTex;
    private Button mBtnSetReminder;
    private AlarmManager alarmMgr;
    private PendingIntent alarmIntent;

    /*TODO: Cancel Alarms*/

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottomsheet_cleanhouse, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Generics.createNotificationChannel(getContext(), "Clean House", "Clean your house regularly", "cleanHouse");

        initViews(view);
    }

    private void initViews(View view) {
        mDayEditTex = view.findViewById(R.id.house_editday);
        mBtnSetReminder = view.findViewById(R.id.btn_set_reminder_house);

        mBtnSetReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String reminderDays = String.valueOf(mDayEditTex.getText()).trim();
                if (!reminderDays.equals(null) && !reminderDays.equals("")) {
                    cleanHouseReminder(Integer.valueOf(String.valueOf(mDayEditTex.getText())));
                    dismiss();
                } else {
                    dismiss();
                }
            }
        });

    }

    private void cleanHouseReminder(int hour) {

        alarmMgr = (AlarmManager) getContext().getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(getContext(), AlertReceiver.class);
        intent.putExtra("channelId", "cleanHouse");
        alarmIntent = PendingIntent.getBroadcast(getContext(), 3, intent, 0);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 9);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        if (calendar.before(Calendar.getInstance())) {
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), TimeUnit.MILLISECONDS.convert(hour, TimeUnit.DAYS), alarmIntent);
        Generics.setSharedPref(getContext(),"pref_cleanHouse", "true");
        Toast.makeText(getContext(), "We will remind you every " + hour + " days at 9.00 AM", Toast.LENGTH_SHORT).show();
    }
}


