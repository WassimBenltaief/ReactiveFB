package com.beltaief.reactivefb.models;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;

import com.beltaief.reactivefb.util.GraphPath;
import com.beltaief.reactivefb.util.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.List;

public class Photo implements Publishable {

    public Photo() {
    }

    public static final String ID = "id";
    public static final String ALBUM = "album";
    public static final String BACKDATED_TIME = "backdated_time";
    public static final String BACKDATED_TIME_GRANULARITY = "backdate_time_granularity";
    public static final String CREATED_TIME = "created_time";
    public static final String FROM = "from";
    public static final String HEIGHT = "height";
    public static final String ICON = "icon";
    public static final String IMAGES = "images";
    public static final String LINK = "link";
    public static final String PAGE_STORY_ID = "page_story_id";
    public static final String PICTURE = "picture";
    public static final String PLACE = "place";
    public static final String SOURCE = "source";
    public static final String UPDATED_TIME = "updated_time";
    public static final String WIDTH = "width";
    public static final String NAME = "name";
    public static final String MESSAGE = "message"; // same as NAME
    public static final String PRIVACY = "privacy";

    private String mId;

    private Album mAlbum;

    private Date mBackDatetime;

    private BackDatetimeGranularity mBackDatetimeGranularity;

    private Date mCreatedTime;

    private User mFrom;

    private Integer mHeight;

    private String mIcon;

    private List<Image> mImages;

    private String mLink;

    private String mName;

    private String mPageStoryId;

    private String mPicture;

    private String mSource;

    private Date mUpdatedTime;

    private Integer mWidth;

    private Place mPlace;

    private String mPlaceId = null;
    private Parcelable mParcelable = null;
    private byte[] mBytes = null;
    private Privacy mPrivacy = null;


    public void setId(String id) {
        mId = id;
    }

    public void setAlbum(Album album) {
        mAlbum = album;
    }

    public void setBackDatetime(Date backDatetime) {
        mBackDatetime = backDatetime;
    }

    public void setBackDatetimeGranularity(BackDatetimeGranularity backDatetimeGranularity) {
        mBackDatetimeGranularity = backDatetimeGranularity;
    }

    public void setCreatedTime(Date createdTime) {
        mCreatedTime = createdTime;
    }

    public void setFrom(User from) {
        mFrom = from;
    }

    public void setHeight(Integer height) {
        mHeight = height;
    }

    public void setIcon(String icon) {
        mIcon = icon;
    }

    public void setImages(List<Image> images) {
        mImages = images;
    }

    public void setLink(String link) {
        mLink = link;
    }

    public void setName(String name) {
        mName = name;
    }

    public void setPageStoryId(String pageStoryId) {
        mPageStoryId = pageStoryId;
    }

    public void setPicture(String picture) {
        mPicture = picture;
    }

    public void setSource(String source) {
        mSource = source;
    }

    public void setUpdatedTime(Date updatedTime) {
        mUpdatedTime = updatedTime;
    }

    public void setWidth(Integer width) {
        mWidth = width;
    }

    public void setPlace(Place place) {
        mPlace = place;
    }

    public void setPlaceId(String placeId) {
        mPlaceId = placeId;
    }

    public void setParcelable(Parcelable parcelable) {
        mParcelable = parcelable;
    }

    public void setBytes(byte[] bytes) {
        mBytes = bytes;
    }

    public void setPrivacy(Privacy privacy) {
        mPrivacy = privacy;
    }

    private Photo(Builder builder) {
        mName = builder.mName;
        mPlaceId = builder.mPlaceId;
        mParcelable = builder.mParcelable;
        mBytes = builder.mBytes;
        mPrivacy = builder.mPrivacy;
    }

    @Override
    public String getPath() {
        return GraphPath.PHOTOS;
    }

    @Override
    public Permission getPermission() {
        return Permission.PUBLISH_ACTION;
    }

    /**
     * Get id of the photo
     *
     * @return
     */
    public String getId() {
        return mId;
    }

    public Album getAlbum() {
        return mAlbum;
    }

    public Date getBackDateTime() {
        return mBackDatetime;
    }

    public BackDatetimeGranularity getBackDatetimeGranularity() {
        return mBackDatetimeGranularity;
    }

    public Date getCreatedTime() {
        return mCreatedTime;
    }

    public User getFrom() {
        return mFrom;
    }

    public Integer getHeight() {
        return mHeight;
    }

    public String getIcon() {
        return mIcon;
    }

    public List<Image> getImages() {
        return mImages;
    }

    public String getLink() {
        return mLink;
    }

    public String getName() {
        return mName;
    }

    public String getPageStoryId() {
        return mPageStoryId;
    }

    public String getPicture() {
        return mPicture;
    }

    public Place getPlace() {
        return mPlace;
    }

    public String getSource() {
        return mSource;
    }

    public Date getUpdatedTime() {
        return mUpdatedTime;
    }

    public Integer getWidth() {
        return mWidth;
    }

    /**
     * Is used for publishing action
     */
    public Parcelable getParcelable() {
        return mParcelable;
    }

    /**
     * Is used for publishing action
     */
    public String getPlaceId() {
        return mPlaceId;
    }

    public Bundle getBundle() {
        Bundle bundle = new Bundle();

        // add description
        if (mName != null) {
            bundle.putString(MESSAGE, mName);
        }

        // add place
        if (mPlaceId != null) {
            bundle.putString(PLACE, mPlaceId);
        }

        // add privacy
        if (mPrivacy != null) {
            bundle.putString(PRIVACY, mPrivacy.getJSONString());
        }

        // add image
        if (mParcelable != null) {
            bundle.putParcelable(PICTURE, mParcelable);
        } else if (mBytes != null) {
            bundle.putByteArray(PICTURE, mBytes);
        }

        return bundle;
    }

    public enum BackDatetimeGranularity {
        YEAR("year"),
        MONTH("month"),
        DAY("day"),
        HOUR("hour"),
        MIN("min"),
        NONE("none");

        private String mValue;

        private BackDatetimeGranularity(String value) {
            mValue = value;
        }

        public String getValue() {
            return mValue;
        }

        public static BackDatetimeGranularity fromValue(String value) {
            for (BackDatetimeGranularity granularityEnum : values()) {
                if (granularityEnum.mValue.equals(value)) {
                    return granularityEnum;
                }
            }
            return BackDatetimeGranularity.NONE;
        }
    }

    /**
     * Builder for preparing the Photo object to be published.
     */
    public static class Builder {
        private String mName = null;
        private String mPlaceId = null;

        private Parcelable mParcelable = null;
        private byte[] mBytes = null;
        private Privacy mPrivacy = null;

        public Builder() {
        }

        /**
         * Set photo to be published
         *
         * @param bitmap
         */
        public Builder setImage(Bitmap bitmap) {
            mParcelable = bitmap;
            return this;
        }

        /**
         * Set photo to be published
         *
         * @param file
         */
        public Builder setImage(File file) {
            try {
                mParcelable = ParcelFileDescriptor.open(file, ParcelFileDescriptor.MODE_READ_ONLY);
            } catch (FileNotFoundException e) {
                Logger.logError(Photo.class, "Failed to create photo from file", e);
            }
            return this;
        }

        /**
         * Set photo to be published
         *
         * @param bytes
         */
        public Builder setImage(byte[] bytes) {
            mBytes = bytes;
            return this;
        }

        /**
         * Add name/description to the photo
         *
         * @param name The name/description of the photo
         */
        public Builder setName(String name) {
            mName = name;
            return this;
        }

        /**
         * Add place id of the photo
         *
         * @param placeId The place id of the photo
         */
        public Builder setPlace(String placeId) {
            mPlaceId = placeId;
            return this;
        }

        /**
         * Add privacy setting to the photo
         *
         * @param privacy The privacy setting of the photo
         * @see Privacy
         */
        public Builder setPrivacy(Privacy privacy) {
            mPrivacy = privacy;
            return this;
        }

        public Photo build() {
            return new Photo(this);
        }
    }

    @Override
    public String toString() {
        return mSource;
    }
}
