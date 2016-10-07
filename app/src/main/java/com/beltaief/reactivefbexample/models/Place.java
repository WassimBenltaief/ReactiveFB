package com.beltaief.reactivefbexample.models;

import android.location.Location;

import com.google.gson.annotations.SerializedName;

public class Place extends IdName{

    private static final String LOCATION = "location";

    @SerializedName(LOCATION)
    private Location mLocation;

    public Location getLocation() {
        return mLocation;
    }
}