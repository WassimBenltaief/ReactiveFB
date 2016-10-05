package com.beltaief.reactivefb.models;



public class Place extends IdName{

    private static final String LOCATION = "location";

    private Location mLocation;

    public Location getLocation() {
        return mLocation;
    }

    public void setLocation(Location location) {
        mLocation = location;
    }

}