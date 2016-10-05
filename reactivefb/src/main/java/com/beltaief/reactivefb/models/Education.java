package com.beltaief.reactivefb.models;

import java.util.List;

/**
 * @author sromku
 * // @see https://developers.facebook.com/docs/reference/fql/user/
 */
public class Education {

    private static final String SCHOOL = "school";
    private static final String DEGREE = "degree";
    private static final String YEAR = "year";
    private static final String CONCENTRATION = "concentration";
    private static final String TYPE = "type";
    private static final String WITH = "with";

    private IdName mSchool;

    private IdName mDegree;

    private IdName mYear;

    private List<IdName> mConcentration;

    private List<User> mWith;

    private String mType;

    public IdName getSchool() {
        return mSchool;
    }

    public void setSchool(IdName school) {
        mSchool = school;
    }

    public IdName getDegree() {
        return mDegree;
    }

    public void setDegree(IdName degree) {
        mDegree = degree;
    }

    public IdName getYear() {
        return mYear;
    }

    public void setYear(IdName year) {
        mYear = year;
    }

    public List<IdName> getConcentration() {
        return mConcentration;
    }

    public void setConcentration(List<IdName> concentration) {
        mConcentration = concentration;
    }

    public List<User> getWith() {
        return mWith;
    }

    public void setWith(List<User> with) {
        mWith = with;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }
}
