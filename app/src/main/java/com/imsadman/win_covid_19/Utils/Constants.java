package com.imsadman.win_covid_19.Utils;

import android.app.Activity;

public class Constants {
    private static final String TAG = Activity.class.getSimpleName();

    private static String PLACES_API_KEY = "AIzaSyBYPIXZInwFjimC27DfhrzYSDUUdNk1cAk";

    public static String getPlacesApiKey() {
        return PLACES_API_KEY;
    }
}
