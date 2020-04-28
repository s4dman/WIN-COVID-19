package com.imsadman.win_covid_19.ui.community;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.imsadman.win_covid_19.R;
import com.imsadman.win_covid_19.models.ProductEntity;
import com.imsadman.win_covid_19.utils.Generics;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RequestsFragment extends Fragment {
    private static final String TAG = "RequestsFragment";

    private EditText mProductName, mProductQuantity, mProductCategory, mPhoneNumber;
    private RecyclerView mRecyclerView;
    private FloatingActionButton mFloatingActionButton;
    private ArrayList<ProductEntity> mProductEntitiesList = new ArrayList<>();
    private String mUid;


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

        if (Generics.getUser() != null) {
            mUid = Generics.getUser().getUid();
        }

        getRequestedProducts();
        onclick();
    }

    private void onclick() {
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mUid != null) {
                    /*TODO: Cleanup and Data Validation*/
                    View dialog = LayoutInflater.from(getContext()).inflate(R.layout.layout_post_product, null);
                    AlertDialog.Builder alertDialogBuilderList = new AlertDialog.Builder(getContext());
                    alertDialogBuilderList.setView(dialog);

                    mProductName = dialog.findViewById(R.id.edit_product_name);
                    mProductQuantity = dialog.findViewById(R.id.edit_product_quantity);
                    mProductCategory = dialog.findViewById(R.id.edit_product_category);
                    mPhoneNumber = dialog.findViewById(R.id.edit_phone_number);

                    alertDialogBuilderList.setCancelable(true)
                            .setTitle(R.string.TEXT_REQUEST)
                            .setPositiveButton("REQUEST", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                    String name = mProductName.getText().toString();
                                    String quantity = mProductQuantity.getText().toString();
                                    String category = mProductCategory.getText().toString();
                                    String phoneNumber = mPhoneNumber.getText().toString();

                                    if (!name.equals("") && !quantity.equals("") && !category.equals("") && !phoneNumber.equals("")) {
                                        postRequests(name, Integer.parseInt(quantity), category, phoneNumber);
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
                } else {
                    Toast.makeText(getContext(), "Login to Create Request", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void postRequests(String name, int quantity, String category, String phoneNumber) {

        DocumentReference documentReference = Generics.initFirestore(getContext()).collection("requested_products").document();
        ProductEntity productEntity = new ProductEntity();

        productEntity.setRequested_name(name);
        productEntity.setRequested_quantity(quantity);
        productEntity.setRequested_category(category);
        productEntity.setRequested_phone_number(phoneNumber);
        productEntity.setUid(mUid);
        productEntity.setRequested_products_id(documentReference.getId());

        documentReference
                .set(productEntity)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getContext(), "Product Added", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(), "Error! Please Try Again", Toast.LENGTH_SHORT).show();
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
        RequestsAdapter requestsAdapter = new RequestsAdapter(getContext(), result);
        mRecyclerView.setAdapter(requestsAdapter);
    }
}
