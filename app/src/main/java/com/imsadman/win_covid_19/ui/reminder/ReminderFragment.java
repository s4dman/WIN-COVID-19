package com.imsadman.win_covid_19.ui.reminder;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.imsadman.win_covid_19.R;

import java.util.Calendar;

import static android.content.Context.ALARM_SERVICE;

public class ReminderFragment extends Fragment {
    private static final String TAG = "ReminderFragment";

    private Button mBtnHandWash, mBtnAvoidTouch, mBtnCleanHouse;
    private PendingIntent alarmIntent;
    private ReminderViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        dashboardViewModel = new ViewModelProvider(this).get(ReminderViewModel.class);
        View root = inflater.inflate(R.layout.fragment_reminder, container, false);

        final TextView textView = root.findViewById(R.id.text_dashboard);

        dashboardViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        createNotificationChannel();
        initViews(view);
    }

    private void initViews(View view) {
        mBtnHandWash = view.findViewById(R.id.btn_set_handwash);
        mBtnAvoidTouch = view.findViewById(R.id.btn_set_avoid_touch);
        mBtnCleanHouse = view.findViewById(R.id.btn_set_house_clean);

        onClick();
    }

    private void onClick() {
        mBtnHandWash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handWashReminder();
            }
        });
    }

    private void handWashReminder() {

        Intent intent = new Intent(getContext(), AlertReceiver.class);
        alarmIntent = PendingIntent.getBroadcast(getContext(), 1, intent, 0);
        AlarmManager alarmManager = (AlarmManager) getContext().getSystemService(ALARM_SERVICE);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR));
        calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, calendar.get(Calendar.SECOND) + 10);

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 10000, AlarmManager.INTERVAL_HOUR, alarmIntent);

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