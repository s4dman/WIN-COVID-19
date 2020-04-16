package com.imsadman.win_covid_19.ui.reminder;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.imsadman.win_covid_19.R;

import java.util.Calendar;

import static android.content.Context.ALARM_SERVICE;

public class ReminderFragment extends Fragment {
    private static final String TAG = "ReminderFragment";

    private Button mBtnHandWash, mBtnAvoidTouch, mBtnCleanHouse;
    private PendingIntent alarmIntent;
    private ReminderViewModel dashboardViewModel;
    private Context mContext;

    private static int ONE_HOUR_INTERVAL_IN_MS = 3600000;
    private static int TWO_HOUR_INTERVAL_IN_MS = 7200000;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }

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
                ReminderBottomSheetDialog reminderBottomSheetDialog = new ReminderBottomSheetDialog();
                reminderBottomSheetDialog.show(getChildFragmentManager(), "reminderBottomSheetDialog");
            }
        });
    }




}