package com.imsadman.win_covid_19.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.Geofence;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;
import com.imsadman.win_covid_19.R;
import com.imsadman.win_covid_19.Utils.Constants;

import java.util.Arrays;

public class LocationActivity extends AppCompatActivity {
    private static final String TAG = Activity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        init();
    }


    private void init() {
        initViews();
        initPlaces();
    }


    private void initViews() { }


    private void initPlaces() {

        if (!Places.isInitialized()) {
            Places.initialize(getApplicationContext(), Constants.getPlacesApiKey());
        } else {
            Log.d(TAG, "Error Places Initialization!");
        }

        PlacesClient placesClient = Places.createClient(this);

        AutocompleteSupportFragment autocompleteFragment = (AutocompleteSupportFragment)
                getSupportFragmentManager().findFragmentById(R.id.autocomplete_fragment);

        autocompleteFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME));

        getPlaces(autocompleteFragment);

    }

    private void getPlaces(AutocompleteSupportFragment autocompleteFragment) {
        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                Log.i(TAG, "Place: " + place.getName() + ", " + place.getId());
            }

            @Override
            public void onError(Status status) {
                Log.i(TAG, "An error occurred: " + status);
            }
        });
    }
}
