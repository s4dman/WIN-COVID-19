package com.imsadman.win_covid_19.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;
import com.imsadman.win_covid_19.R;
import com.imsadman.win_covid_19.utils.Constants;
import com.imsadman.win_covid_19.utils.Generics;

import java.util.Arrays;

public class LocationActivity extends AppCompatActivity {
    private static final String TAG = Activity.class.getSimpleName();
    private TextView mSaveBtn;
    private String mLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLocation = Generics.getSharedPreferences(this).getString("PREF_LOCATION", null);
        if (mLocation != null && !"".equals(mLocation)) {
            startActivity(new Intent(LocationActivity.this, MainActivity.class));
        }

        setContentView(R.layout.activity_location);
        init();
    }


    private void init() {
        initViews();
        initPlaces();
    }


    private void initViews() {
        mSaveBtn = findViewById(R.id.btn_save_location);
        mSaveBtn.setText(R.string.continue_btn);
        onClick();
    }

    private void onClick() {
        mSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Generics.setSharedPref(getApplicationContext(), "PREF_LOCATION", mLocation);
                Intent mainActivity = new Intent(LocationActivity.this, MainActivity.class);
                startActivity(mainActivity);
            }
        });
    }

    private void initPlaces() {

        if (!Places.isInitialized()) {
            Places.initialize(getApplicationContext(), Constants.getPlacesApiKey());
        } else {
            Log.d(TAG, "Error Places Initialization!");
        }

        PlacesClient placesClient = Places.createClient(this);

        AutocompleteSupportFragment autocompleteFragment = (AutocompleteSupportFragment)
                getSupportFragmentManager().findFragmentById(R.id.autocomplete_fragment);

        autocompleteFragment.setPlaceFields(Arrays.asList(Place.Field.ADDRESS_COMPONENTS, Place.Field.NAME));

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                Log.i(TAG, "Place: " + place.getName() + ", " + place.getAddressComponents().asList().get(3).getName());
                mLocation = place.getAddressComponents().asList().get(3).getName();
                mSaveBtn.setEnabled(true);
            }

            @Override
            public void onError(Status status) {
                Log.i(TAG, "An error occurred: " + status);
            }
        });

    }
}