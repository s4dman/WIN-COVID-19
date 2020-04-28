package com.imsadman.win_covid_19.models;

import com.google.firebase.firestore.IgnoreExtraProperties;

@IgnoreExtraProperties
public class RequestedEntity {
    private static final String TAG = "RequestedEntity";

    private String uid;
    private String requested_products_id;
    private String requested_name;
    private int requested_quantity;
    private String requested_category;
    private String requested_phone_number;

    public RequestedEntity() {
    }

    public RequestedEntity(String uid, String requested_products_id, String requested_name, int requested_quantity, String requested_category, String requested_phone_number) {
        this.uid = uid;
        this.requested_products_id = requested_products_id;
        this.requested_name = requested_name;
        this.requested_quantity = requested_quantity;
        this.requested_category = requested_category;
        this.requested_phone_number = requested_phone_number;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getRequested_products_id() {
        return requested_products_id;
    }

    public void setRequested_products_id(String requested_products_id) {
        this.requested_products_id = requested_products_id;
    }

    public String getRequested_name() {
        return requested_name;
    }

    public void setRequested_name(String requested_name) {
        this.requested_name = requested_name;
    }

    public int getRequested_quantity() {
        return requested_quantity;
    }

    public void setRequested_quantity(int requested_quantity) {
        this.requested_quantity = requested_quantity;
    }

    public String getRequested_category() {
        return requested_category;
    }

    public void setRequested_category(String requested_category) {
        this.requested_category = requested_category;
    }

    public String getRequested_phone_number() {
        return requested_phone_number;
    }

    public void setRequested_phone_number(String requested_phone_number) {
        this.requested_phone_number = requested_phone_number;
    }
}
