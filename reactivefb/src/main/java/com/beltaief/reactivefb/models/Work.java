package com.beltaief.reactivefb.models;



public class Work {

    //	private static final String NAME = "name";
    private static final String EMPLOYER = "employer";
    private static final String LOCATION = "location";
    private static final String POSITION = "position";
    private static final String DESCRIPTION = "description";
    private static final String START_DATE = "start_date";
    private static final String END_DATE = "end_date";


    private User mEmployer;

    private Location mLocation;

    private IdName mPosition;

    private String mDescription;

    private String mStartDate;

    private String mEndDate;

    public User getEmployer() {
        return mEmployer;
    }

    public void setEmployer(User employer) {
        mEmployer = employer;
    }

    public Location getLocation() {
        return mLocation;
    }

    public void setLocation(Location location) {
        mLocation = location;
    }

    public IdName getPosition() {
        return mPosition;
    }

    public void setPosition(IdName position) {
        mPosition = position;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getStartDate() {
        return mStartDate;
    }

    public void setStartDate(String startDate) {
        mStartDate = startDate;
    }

    public String getEndDate() {
        return mEndDate;
    }

    public void setEndDate(String endDate) {
        mEndDate = endDate;
    }
}
