package com.beltaief.reactivefb.models;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

import java.util.Locale;

/**
 * Created by sromku on 6/9/15.
 */
@JsonObject
public class IdName {

    private static final String ID = "id";
    private static final String NAME = "name";

    @JsonField(name = ID)
    protected String mId;

    @JsonField(name = NAME)
    protected String mName;

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
