package com.imsadman.win_covid_19.models;

import com.google.firebase.firestore.IgnoreExtraProperties;

@IgnoreExtraProperties
public class ProductEntity {
    private static final String TAG = "ProductEntity";

    private String uid;
    private String offered_products_id;
    private String offered_name;
    private int offered_quantity;
    private String offered_category;
    private String offered_phone_number;
    private String requested_products_id;
    private String requested_name;
    private int requested_quantity;
    private String requested_category;
    private String requested_phone_number;

    public ProductEntity() {
    }

    public ProductEntity(String uid, String offered_products_id, String offered_name, int offered_quantity, String offered_category, String offered_phone_number, String requested_products_id, String requested_name, int requested_quantity, String requested_category, String requested_phone_number) {
        this.uid = uid;
        this.offered_products_id = offered_products_id;
        this.offered_name = offered_name;
        this.offered_quantity = offered_quantity;
        this.offered_category = offered_category;
        this.offered_phone_number = offered_phone_number;
        this.requested_products_id = requested_products_id;
        this.requested_name = requested_name;
        this.requested_quantity = requested_quantity;
        this.requested_category = requested_category;
        this.requested_phone_number = requested_phone_number;
    }

    public String getUid() {
        return uid;
    }

    public String getOffered_products_id() {
        return offered_products_id;
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

    public String getRequested_products_id() {
        return requested_products_id;
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

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setOffered_products_id(String offered_products_id) {
        this.offered_products_id = offered_products_id;
    }

    public void setOffered_name(String offered_name) {
        this.offered_name = offered_name;
    }

    public void setOffered_quantity(int offered_quantity) {
        this.offered_quantity = offered_quantity;
    }

    public void setOffered_category(String offered_category) {
        this.offered_category = offered_category;
    }

    public void setOffered_phone_number(String offered_phone_number) {
        this.offered_phone_number = offered_phone_number;
    }

    public void setRequested_products_id(String requested_products_id) {
        this.requested_products_id = requested_products_id;
    }

    public void setRequested_name(String requested_name) {
        this.requested_name = requested_name;
    }

    public void setRequested_quantity(int requested_quantity) {
        this.requested_quantity = requested_quantity;
    }

    public void setRequested_category(String requested_category) {
        this.requested_category = requested_category;
    }

    public void setRequested_phone_number(String requested_phone_number) {
        this.requested_phone_number = requested_phone_number;
    }
}
