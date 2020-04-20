package com.imsadman.win_covid_19.models;

import com.google.firebase.firestore.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Product {
    private static final String TAG = "Product";

    private String product_id;
    private String product_name;
    private String product_quantity;
    private String product_category;
    private String phone_number;

    public Product(String product_id, String product_name, String product_quantity, String product_category, String phone_number) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.product_quantity = product_quantity;
        this.product_category = product_category;
        this.phone_number = phone_number;
    }

    public static String getTAG() {
        return TAG;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_quantity() {
        return product_quantity;
    }

    public void setProduct_quantity(String product_quantity) {
        this.product_quantity = product_quantity;
    }

    public String getProduct_category() {
        return product_category;
    }

    public void setProduct_category(String product_category) {
        this.product_category = product_category;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }
}
