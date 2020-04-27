package com.imsadman.win_covid_19.models;

import com.google.firebase.firestore.IgnoreExtraProperties;

@IgnoreExtraProperties
public class ProductEntity {
    private static final String TAG = "ProductEntity";

    private String uid;
    private String offered_id;
    private String offered_name;
    private int offered_quantity;
    private String offered_category;
    private String offered_phone_number;
    private String requested_product_id;
    private String requested_name;
    private int requested_quantity;
    private String requested_category;
    private String requested_phone_number;

    public ProductEntity() {
    }

    public ProductEntity(String uid, String offered_id, String offered_name, int offered_quantity, String offered_category, String offered_phone_number, String requested_product_id, String requested_name, int requested_quantity, String requested_category, String requested_phone_number) {
        this.uid = uid;
        this.offered_id = offered_id;
        this.offered_name = offered_name;
        this.offered_quantity = offered_quantity;
        this.offered_category = offered_category;
        this.offered_phone_number = offered_phone_number;
        this.requested_product_id = requested_product_id;
        this.requested_name = requested_name;
        this.requested_quantity = requested_quantity;
        this.requested_category = requested_category;
        this.requested_phone_number = requested_phone_number;
    }

    public String getUid() {
        return uid;
    }

    public String getOffered_id() {
        return offered_id;
    }

    public String getOffered_name() {
        return offered_name;
    }

    public int getOffered_quantity() {
        return offered_quantity;
    }

    public String getOffered_category() {
        return offered_category;
    }

    public String getOffered_phone_number() {
        return offered_phone_number;
    }

    public String getRequested_product_id() {
        return requested_product_id;
    }

    public String getRequested_name() {
        return requested_name;
    }

    public int getRequested_quantity() {
        return requested_quantity;
    }

    public String getRequested_category() {
        return requested_category;
    }

    public String getRequested_phone_number() {
        return requested_phone_number;
    }
}
