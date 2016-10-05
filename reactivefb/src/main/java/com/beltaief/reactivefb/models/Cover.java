package com.beltaief.reactivefb.models;


import java.util.Date;

/**
 * Created by sromku on 6/9/15.
 */
public class Cover {

    public static final String ID = "id";
    public static final String SOURCE = "source";
    public static final String CREATED_TIME = "created_time";
    public static final String OFFSET_X = "offset_x";
    public static final String OFFSET_Y = "offset_y";
    public static final String COVER_ID = "cover_id";

    private String mId = null;

    private String mSource = null;

    private String mOffsetX = null;

    private String mOffsetY = null;

    private String mCoverId = null;

    private Date mCreatedTime = null;

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getSource() {
        return mSource;
    }

    public void setSource(String source) {
        mSource = source;
    }

    public String getOffsetX() {
        return mOffsetX;
    }

    public void setOffsetX(String offsetX) {
        mOffsetX = offsetX;
    }

    public String getOffsetY() {
        return mOffsetY;
    }

    public void setOffsetY(String offsetY) {
        mOffsetY = offsetY;
    }

    public String getCoverId() {
        return mCoverId;
    }

    public void setCoverId(String coverId) {
        mCoverId = coverId;
    }

    public Date getCreatedTime() {
        return mCreatedTime;
    }

    public void setCreatedTime(Date createdTime) {
        mCreatedTime = createdTime;
    }
}
