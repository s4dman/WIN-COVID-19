package com.imsadman.win_covid_19.models;

public class OfferedEntity {
    private static final String TAG = "OfferedEntity";

    private String uid;
    private String offered_products_id;
    private String offered_name;
    private int offered_quantity;
    private String offered_category;
    private String offered_phone_number;

    public OfferedEntity() {
    }

    public OfferedEntity(String uid, String offered_products_id, String offered_name, int offered_quantity, String offered_category, String offered_phone_number) {
        this.uid = uid;
        this.offered_products_id = offered_products_id;
        this.offered_name = offered_name;
        this.offered_quantity = offered_quantity;
        this.offered_category = offered_category;
        this.offered_phone_number = offered_phone_number;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getOffered_products_id() {
        return offered_products_id;
    }

    public void setOffered_products_id(String offered_products_id) {
        this.offered_products_id = offered_products_id;
    }

    public String getOffered_name() {
        return offered_name;
    }

    public void setOffered_name(String offered_name) {
        this.offered_name = offered_name;
    }

    public int getOffered_quantity() {
        return offered_quantity;
    }

    public void setOffered_quantity(int offered_quantity) {
        this.offered_quantity = offered_quantity;
    }

    public String getOffered_category() {
        return offered_category;
    }

    public void setOffered_category(String offered_category) {
        this.offered_category = offered_category;
    }

    public String getOffered_phone_number() {
        return offered_phone_number;
    }

    public void setOffered_phone_number(String offered_phone_number) {
        this.offered_phone_number = offered_phone_number;
    }
}
