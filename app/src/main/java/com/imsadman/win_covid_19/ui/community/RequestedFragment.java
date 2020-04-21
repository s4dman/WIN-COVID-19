package com.imsadman.win_covid_19.ui.community;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.imsadman.win_covid_19.R;
import com.imsadman.win_covid_19.models.ProductEntity;
import com.imsadman.win_covid_19.utils.Generics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RequestedFragment extends Fragment {
    private static final String TAG = "RequestedFragment";
    private RecyclerView mRecyclerView;
    private FloatingActionButton mFloatingActionButton;
    private ArrayList<ProductEntity> mProductEntitiesList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_requests, container, false);
        mRecyclerView = root.findViewById(R.id.recycler_requested_products);
        mFloatingActionButton = root.findViewById(R.id.request_floatingActionButton);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getRequestedProducts();
        onclick();
    }

    private void onclick() {
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postRequests();
            }
        });
    }

    private void postRequests() {
        Map<String, Object> product = new HashMap<>();
        product.put("requested_name", "White Eggs");
        product.put("requested_quantity", 10);
        product.put("requested_category", "Food");
        product.put("requested_phone_number", "519 971 0000");

        Generics.initFirestore(getContext()).collection("requested_products")
                .add(product)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });
    }

    private void getOfferedProducts() {

        Generics.initFirestore(getContext()).collection("offered_products")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                ProductEntity productEntity = document.toObject(ProductEntity.class);
                                mProductEntitiesList.add(productEntity);
                                initRecyclerView(mProductEntitiesList);
                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
    }

    private void getRequestedProducts() {
        Generics.initFirestore(getContext()).collection("requested_products")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                ProductEntity productEntity = document.toObject(ProductEntity.class);
                                mProductEntitiesList.add(productEntity);
                                initRecyclerView(mProductEntitiesList);
                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
    }

    private void initRecyclerView(ArrayList<ProductEntity> result) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        RequestedAdapter requestedAdapter = new RequestedAdapter(getContext(), result);
        mRecyclerView.setAdapter(requestedAdapter);
    }
}
