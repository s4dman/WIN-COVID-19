package com.imsadman.win_covid_19.ui.community;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.FirebaseFirestore;
import com.imsadman.win_covid_19.R;

public class CommunityFragment extends Fragment {
    private static final String TAG = "CommunityFragment";

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_community, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        BottomNavigationView navView = view.findViewById(R.id.community_nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_offers, R.id.navigation_requests)
                .build();
        NavController navController = Navigation.findNavController((Activity) getContext(), R.id.community_nav_host_fragment);
        NavigationUI.setupActionBarWithNavController((AppCompatActivity) getContext(), navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        initFirebase();

    }

    private void initFirebase() {
        FirebaseApp.initializeApp(getContext());
        FirebaseFirestore db = FirebaseFirestore.getInstance();

    }
}