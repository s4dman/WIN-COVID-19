package com.imsadman.win_covid_19.ui.community;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
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
import com.yarolegovich.lovelydialog.LovelyCustomDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class OffersFragment extends Fragment {
    private static final String TAG = "OffersFragment";

    private EditText mProductName, mProductQuantity, mProductCategory, mPhoneNumber;
    private RecyclerView mRecyclerView;
    private FloatingActionButton mFloatingActionButton;
    private ArrayList<ProductEntity> mProductEntitiesList = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_offers, container, false);
        initViews(root);
        return root;
    }


    private void initViews(View root) {
        mRecyclerView = root.findViewById(R.id.recycler_offered_products);
        mFloatingActionButton = root.findViewById(R.id.offer_floatingActionButton);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        onclick();
        getOfferedProducts();
    }

    private void onclick() {
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*TODO: Cleanup and Data Validation*/
                View dialog = LayoutInflater.from(getContext()).inflate(R.layout.layout_post_product, null);
                AlertDialog.Builder alertDialogBuilderList = new AlertDialog.Builder(getContext());
                alertDialogBuilderList.setView(dialog);

                mProductName = dialog.findViewById(R.id.edit_product_name);
                mProductQuantity = dialog.findViewById(R.id.edit_product_quantity);
                mProductCategory = dialog.findViewById(R.id.edit_product_category);
                mPhoneNumber = dialog.findViewById(R.id.edit_phone_number);

                alertDialogBuilderList.setCancelable(true)
                        .setTitle(R.string.TEXT_SHARE)
                        .setPositiveButton("SHARE", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                String name = mProductName.getText().toString();
                                String quantity = mProductQuantity.getText().toString();
                                String category = mProductCategory.getText().toString();
                                String phoneNumber = mPhoneNumber.getText().toString();

                                if (!name.equals("") && !quantity.equals("") && !category.equals("") && !phoneNumber.equals("")) {
                                    postOffers(name, Integer.parseInt(quantity), category, phoneNumber);
                                } else
                                    Toast.makeText(getContext(), "ERROR! Must fill all the fields.", Toast.LENGTH_LONG).show();

                            }
                        })
                        .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                AlertDialog alertDialog = alertDialogBuilderList.create();
                alertDialog.show();
                alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(ContextCompat.getColor(getContext(), R.color.darkRed));

            }
        });
    }


    private void postOffers(String name, int quantity, String category, String phoneNumber) {

        Map<String, Object> product = new HashMap<>();
        product.put("offered_name", name);
        product.put("offered_quantity", quantity);
        product.put("offered_category", category);
        product.put("offered_phone_number", phoneNumber);

        Generics.initFirestore(getContext()).collection("offered_products")
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


    private void initRecyclerView(ArrayList<ProductEntity> result) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        OfferedAdapter offeredAdapter = new OfferedAdapter(getContext(), result);
        mRecyclerView.setAdapter(offeredAdapter);
    }
}
