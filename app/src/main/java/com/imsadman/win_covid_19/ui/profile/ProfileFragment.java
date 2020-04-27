package com.imsadman.win_covid_19.ui.profile;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.imsadman.win_covid_19.R;
import com.imsadman.win_covid_19.ui.MainActivity;
import com.imsadman.win_covid_19.utils.Generics;

public class ProfileFragment extends Fragment {
    private static final String TAG = "ProfileFragment";
    private FirebaseAuth mAuth;
    private TextView mHowdy, mLogin, mSignUp, mLogout, mProductText;
    private RecyclerView mRecyclerView;
    private String mIsLoggedIn, mUserEmail;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mIsLoggedIn = Generics.getSharedPreferences(getContext()).getString("IS_LOGGED_IN", null);
        mUserEmail = Generics.getSharedPreferences(getContext()).getString("USER_EMAIL", null);

        mAuth = FirebaseAuth.getInstance();
        initViews(view);

    }

    private void initViews(View view) {
        mHowdy = view.findViewById(R.id.text_howdy);
        mLogin = view.findViewById(R.id.profile_login);
        mSignUp = view.findViewById(R.id.profile_create);
        mLogout = view.findViewById(R.id.profile_logout);
        mRecyclerView = view.findViewById(R.id.profile_recyclerView);
        mProductText = view.findViewById(R.id.text_profile_product);

        onclick();

        if (mIsLoggedIn != null && mIsLoggedIn.equals("true")) {
            mHowdy.setText("Howdy! " + mUserEmail);
            mLogin.setVisibility(View.GONE);
            mSignUp.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.VISIBLE);
            mLogout.setVisibility(View.VISIBLE);
            mProductText.setVisibility(View.VISIBLE);
        }
    }

    private void onclick() {
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginIntent = new Intent(getContext(), LoginActivity.class);
                startActivity(loginIntent);
            }
        });

        mSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent createIntent = new Intent(getContext(), SignupActivity.class);
                startActivity(createIntent);
            }
        });

        mLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mIsLoggedIn != null && mUserEmail != null) {
                    Generics.removeSharedPreferences(getContext(), "IS_LOGGED_IN");
                    Generics.removeSharedPreferences(getContext(), "USER_EMAIL");
                }
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

}
