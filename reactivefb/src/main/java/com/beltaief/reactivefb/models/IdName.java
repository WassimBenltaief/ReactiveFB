package com.beltaief.reactivefb.models;

import java.util.Locale;

/**
 * Created by sromku on 6/9/15.
 */
public class IdName {

    public static final String ID = "id";
    public static final String NAME = "name";

    protected String mId;

    String mName;

    public void setId(String id) {
        mId = id;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    @Override
    public String toString() {
        return String.format(Locale.US, "id=%s,name=%s", mId, mName);
    }
}
