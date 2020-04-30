package com.imsadman.win_covid_19.ui.profile;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.imsadman.win_covid_19.R;
import com.imsadman.win_covid_19.models.OfferedEntity;
import com.imsadman.win_covid_19.models.RequestedEntity;
import com.imsadman.win_covid_19.ui.MainActivity;
import com.imsadman.win_covid_19.utils.Generics;

import java.util.ArrayList;
import java.util.List;

public class ProfileFragment extends Fragment {
    private static final String TAG = "ProfileFragment";
    private TextView mHowdy, mLogin, mSignUp, mLogout, mProductText;
    private RecyclerView mOfferedView, mRequestedView;
    private ArrayList<OfferedEntity> mOfferedList = new ArrayList<>();
    private ArrayList<RequestedEntity> mRequestedList = new ArrayList<>();
    private String mUid;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (Generics.getUser() != null) {
            mUid = Generics.getUser().getUid();
        }

        initViews(view);
        getProducts();
        onclick();

    }

    private void initViews(View view) {
        mHowdy = view.findViewById(R.id.text_howdy);
        mLogin = view.findViewById(R.id.profile_login);
        mSignUp = view.findViewById(R.id.profile_create);
        mLogout = view.findViewById(R.id.profile_logout);
        mOfferedView = view.findViewById(R.id.profile_offered_recyclerView);
        mRequestedView = view.findViewById(R.id.profile_requested_recyclerView);
        mProductText = view.findViewById(R.id.text_profile_product);


        if (mUid != null) {
            mHowdy.setText("Howdy! " + Generics.getUser().getEmail());
            mLogin.setVisibility(View.GONE);
            mSignUp.setVisibility(View.GONE);
            mOfferedView.setVisibility(View.VISIBLE);
            mRequestedView.setVisibility(View.VISIBLE);
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
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

    }

    private void getProducts() {

        Query offeredQuery = Generics.initFirestore(getContext()).collection("offered_products")
                .whereEqualTo("uid", mUid);
        offeredQuery.get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                            OfferedEntity productEntity = document.toObject(OfferedEntity.class);
                            mOfferedList.add(productEntity);
                        }
                        Log.d(TAG, "onSuccess: " + mOfferedList.size());
                        initOfferedView(mOfferedList);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG, "onFailure: " + e.getMessage());
                    }
                });


        Query requestedQuery = Generics.initFirestore(getContext()).collection("requested_products")
                .whereEqualTo("uid", mUid);
        requestedQuery.get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                            RequestedEntity requestedEntity = document.toObject(RequestedEntity.class);
                            mRequestedList.add(requestedEntity);
                        }
                        initRequestedView(mRequestedList);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG, "onFailure: " + e.getMessage());
                    }
                });

    }

    private void initRequestedView(ArrayList<RequestedEntity> mRequestedList) {
        if (mRequestedList.size() > 0) {
            mProductText.setText("Your items are listed below: ");
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mRequestedView.setLayoutManager(linearLayoutManager);
        UserRequestedAdapter userRequestedAdapter = new UserRequestedAdapter(getContext(), mRequestedList);
        mRequestedView.setAdapter(userRequestedAdapter);
    }


    private void initOfferedView(List<OfferedEntity> mProductEntitiesList) {
        if (mProductEntitiesList.size() > 0) {
            mProductText.setText("Your items are listed below: ");
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mOfferedView.setLayoutManager(linearLayoutManager);
        UserOfferedAdapter userOfferedAdapter = new UserOfferedAdapter(getContext(), mProductEntitiesList);
        mOfferedView.setAdapter(userOfferedAdapter);
    }

}
