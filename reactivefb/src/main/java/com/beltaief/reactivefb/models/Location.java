package com.beltaief.reactivefb.models;

public class Location {

    private static final String STREET = "street";
    private static final String CITY = "city";
    private static final String STATE = "state";
    private static final String COUNTRY = "country";
    private static final String ZIP = "zip";
    private static final String LATITUDE = "latitude";
    private static final String LONGITUDE = "longitude";

    public void setStreet(String street) {
        mStreet = street;
    }

    public void setCity(String city) {
        mCity = city;
    }

    public void setState(String state) {
        mState = state;
    }

    public void setCountry(String country) {
        mCountry = country;
    }

    public void setZip(String zip) {
        mZip = zip;
    }

    public void setLatitude(Double latitude) {
        mLatitude = latitude;
    }

    public void setLongitude(Double longitude) {
        mLongitude = longitude;
    }

    private String mStreet;

    private String mCity;

    private String mState;

    private String mCountry;

    private String mZip;

    private Double mLatitude;

    private Double mLongitude;

    public String getStreet() {
        return mStreet;
    }

    public String getCity() {
        return mCity;
    }

    public String getState() {
        return mState;
    }

    public String getCountry() {
        return mCountry;
    }

    public String getZip() {
        return mZip;
    }

    public Double getLatitude() {
        return mLatitude;
    }

    public Double getLongitude() {
        return mLongitude;
    }

}