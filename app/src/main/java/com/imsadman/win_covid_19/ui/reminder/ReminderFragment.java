package com.imsadman.win_covid_19.ui.reminder;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.imsadman.win_covid_19.R;
import com.imsadman.win_covid_19.utils.Generics;

public class ReminderFragment extends Fragment {
    private static final String TAG = "ReminderFragment";

    private TextView mReminderStatus, mHandWashStatus, mAvoidTouchStatus, mCleanHouseStatus;
    private Button mBtnHandWash, mBtnAvoidTouch, mBtnCleanHouse;
    private ImageView mDelHandWash, mDelAvoidTouch, mDelCleanHouse;
    private AlarmManager alarmMgr;
    private PendingIntent alarmIntent;
    private Context mContext;
    private String mHandWashSet, mAvoidToucSet, mCleanHouseSet;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_reminder, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mHandWashSet = "false";
        mAvoidToucSet = "false";
        mCleanHouseSet = "false";

        initViews(view);
    }


    private void initViews(View view) {
        mBtnHandWash = view.findViewById(R.id.btn_set_handwash);
        mBtnAvoidTouch = view.findViewById(R.id.btn_set_avoid_touch);
        mBtnCleanHouse = view.findViewById(R.id.btn_set_house_clean);
        mReminderStatus = view.findViewById(R.id.available_reminders);
        mHandWashStatus = view.findViewById(R.id.status_hand_wash);
        mAvoidTouchStatus = view.findViewById(R.id.status_avoid_touch);
        mCleanHouseStatus = view.findViewById(R.id.status_house_clean);
        mDelHandWash = view.findViewById(R.id.hand_reminder_delete);
        mDelAvoidTouch = view.findViewById(R.id.touch_reminder_delete);
        mDelCleanHouse = view.findViewById(R.id.house_reminder_delete);

        getAlarmStatus();
        onClick();
    }

    private void getAlarmStatus() {

        mHandWashSet = Generics.getSharedPreferences(getContext()).getString("pref_handWash", null);
        if (mHandWashSet != null && mHandWashSet.equals("true")) {
            mReminderStatus.setText(getString(R.string.your_reminder_text));
            mHandWashStatus.setVisibility(View.VISIBLE);
            mDelHandWash.setVisibility(View.VISIBLE);
        }

        mAvoidToucSet = Generics.getSharedPreferences(getContext()).getString("pref_avoidTouch", null);
        if (mAvoidToucSet != null && mAvoidToucSet.equals("true")) {
            mReminderStatus.setText(getString(R.string.your_reminder_text));
            mAvoidTouchStatus.setVisibility(View.VISIBLE);
            mDelAvoidTouch.setVisibility(View.VISIBLE);
        }


        mCleanHouseSet = Generics.getSharedPreferences(getContext()).getString("pref_cleanHouse", null);
        if (mCleanHouseSet != null && mCleanHouseSet.equals("true")) {
            mReminderStatus.setText(getString(R.string.your_reminder_text));
            mCleanHouseStatus.setVisibility(View.VISIBLE);
            mDelCleanHouse.setVisibility(View.VISIBLE);
        }
    }

    private void onClick() {

        mBtnHandWash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HandWashDialog handWashDialog = new HandWashDialog();
                handWashDialog.show(getChildFragmentManager(), "handWashDialog");
            }
        });

        mBtnAvoidTouch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AvoidTouchDialog avoidTouchDialog = new AvoidTouchDialog();
                avoidTouchDialog.show(getChildFragmentManager(), "avoidTouchDialog");
            }
        });

        mBtnCleanHouse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CleanHouseDialog cleanHouseDialog = new CleanHouseDialog();
                cleanHouseDialog.show(getChildFragmentManager(), "cleanHouseDialog");
            }
        });


        mDelHandWash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Generics.removeAlarm(getContext(), "notifyHandWash", 1, "pref_handWash", "Hand Wash Reminder Canceled");
                mHandWashStatus.setVisibility(View.GONE);
                mDelHandWash.setVisibility(View.GONE);
            }
        });

        mDelAvoidTouch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Generics.removeAlarm(getContext(), "avoidTouching", 2, "pref_avoidTouch", "Avoid Face Touch Reminder Canceled");
                mAvoidTouchStatus.setVisibility(View.GONE);
                mDelAvoidTouch.setVisibility(View.GONE);

            }
        });

        mDelCleanHouse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Generics.removeAlarm(getContext(), "cleanHouse", 3, "pref_cleanHouse", "House Cleaning Reminder Canceled");
                mCleanHouseStatus.setVisibility(View.GONE);
                mDelCleanHouse.setVisibility(View.GONE);
            }
        });
    }


}