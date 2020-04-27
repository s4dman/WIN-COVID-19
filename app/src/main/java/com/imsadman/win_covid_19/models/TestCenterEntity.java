package com.imsadman.win_covid_19.models;

import com.google.firebase.firestore.IgnoreExtraProperties;

@IgnoreExtraProperties
public class TestCenterEntity {
    private static final String TAG = "TestCenterEntity";

    private String center_id;
    private String hospital_name;
    private String city;
    private String website;

    public TestCenterEntity() {
    }

    public TestCenterEntity(String center_id, String hospital_name, String city, String website) {
        this.center_id = center_id;
        this.hospital_name = hospital_name;
        this.city = city;
        this.website = website;
    }


    public String getCenter_id() {
        return center_id;
    }

    public String getHospital_name() {
        return hospital_name;
    }

    public String getCity() {
        return city;
    }

    public String getWebsite() {
        return website;
    }
}
