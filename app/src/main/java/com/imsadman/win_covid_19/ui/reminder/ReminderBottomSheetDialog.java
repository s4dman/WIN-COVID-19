package com.imsadman.win_covid_19.ui.reminder;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
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

import java.util.Calendar;

import static android.content.Context.ALARM_SERVICE;

public class ReminderBottomSheetDialog extends BottomSheetDialogFragment {

    private static final String TAG = "ReminderBottomSheetDial";

    private TextView mDialogText;
    private RadioButton mRadioBtn1, mBtnRadio2;
    private Button mBtnSetReminder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottomsheet_reminder, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        createNotificationChannel();
        initViews(view);
    }

    private void initViews(View view) {
        mDialogText = view.findViewById(R.id.bottomSheet_text);
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

        Intent intent = new Intent(getActivity(), AlertReceiver.class);
        PendingIntent alarmIntent = PendingIntent.getBroadcast(getActivity(), 1, intent, 0);
        AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(ALARM_SERVICE);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR));
        calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) + 1);
        calendar.set(Calendar.SECOND, calendar.get(Calendar.SECOND));

        Log.d(TAG, "handWashReminder: " + calendar.getTime());

        if (hour == 1) {
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 60000, alarmIntent);
            Toast.makeText(getContext(), "We will remind you every 1 minute", Toast.LENGTH_SHORT).show();
        } else {
            Log.d(TAG, "handWashReminder: " + hour);
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 60 * 100 * 120, 120000, alarmIntent);
            Toast.makeText(getContext(), "We will remind you every 2 minutes", Toast.LENGTH_SHORT).show();
        }

    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Safety Reminder";
            String description = "Stay Clean Stay Healthy";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel("notifyHandWash", name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getContext().getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
