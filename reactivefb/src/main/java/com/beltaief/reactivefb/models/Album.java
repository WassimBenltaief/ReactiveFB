package com.beltaief.reactivefb.models;

import android.os.Bundle;

import com.beltaief.reactivefb.util.Converters;
import com.beltaief.reactivefb.util.GraphPath;
import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;
import java.util.Date;

/**
 * @author wassim
 * // @see https://developers.facebook.com/docs/reference/api/album
 */
@JsonObject
public class Album implements Publishable {

    private static final String ID = "id";
    private static final String FROM = "from";
    private static final String NAME = "name";
    private static final String DESCRIPTION = "description";
    private static final String MESSAGE = "message";
    private static final String LOCATION = "location";
    private static final String LINK = "link";
    private static final String COUNT = "count";
    private static final String PRIVACY = "privacy";
    private static final String COVER_PHOTO = "cover_photo";
    private static final String TYPE = "type";
    private static final String CREATED_TIME = "created_time";
    private static final String UPDATED_TIME = "updated_time";
    private static final String CAN_UPLOAD = "can_upload";

    @JsonField(name = ID)
    private String mId = null;

    @JsonField(name = FROM)
    private User mFrom = null;

    @JsonField(name = NAME)
    private String mName = null;

    @JsonField(name = DESCRIPTION)
    private String mDescription = null;

    @JsonField(name = LOCATION)
    private String mLocation = null;

    @JsonField(name = LINK)
    private String mLink = null;

    @JsonField(name = COUNT)
    private Integer mCount = null;

    @JsonField(name = PRIVACY)
    private String mPrivacy = null;

    @JsonField(name = COVER_PHOTO)
    private Cover mCover = null;

    @JsonField(name = TYPE)
    private String mType = null;

    @JsonField(name = CREATED_TIME,
            typeConverter = Converters.DateTimeZoneConverter.class)
    private Date mCreatedTime;

    @JsonField(name = UPDATED_TIME,
            typeConverter = Converters.DateTimeZoneConverter.class)
    private Date mUpdatedTime;

    @JsonField(name = CAN_UPLOAD)
    private boolean mCanUpload;

    private Privacy mPublishPrivacy = null;

    private Album(Builder builder) {
        mName = builder.mName;
        mDescription = builder.mMessage;
        mPublishPrivacy = builder.mPublishPrivacy;
    }

    /**
     * The album id.
     *
     * @return The album id
     */
    public String getId() {
        return mId;
    }

    /**
     * The user who created this album.
     *
     * @return The user who created this album
     */
    public User getFrom() {
        return mFrom;
    }

    /**
     * The title of the album.
     *
     * @return The title of the album
     */
    public String getName() {
        return mName;
    }

    /**
     * The description of the album.
     *
     * @return The description of the album
     */
    public String getDescription() {
        return mDescription;
    }

    /**
     * The location of the album.
     *
     * @return The location of the album
     */
    public String getLocation() {
        return mLocation;
    }

    /**
     * A link to this album on Facebook.
     *
     * @return A link to this album on Facebook
     */
    public String getLink() {
        return mLink;
    }

    /**
     * The number of photos in this album.
     *
     * @return The number of photos in this album
     */
    public Integer getCount() {
        return mCount;
    }

    /**
     * The privacy settings for the album.
     *
     * @return The privacy settings for the album
     */
    public String getPrivacy() {
        return mPrivacy;
    }

    /**
     * The type of the album.
     *
     * @return The type of the album
     */
    public String getType() {
        return mType;
    }

    /**
     * The time the photo album was initially created.
     *
     * @return The time the photo album was initially created
     */
    public Date getCreatedTime() {
        return mCreatedTime;
    }

    /**
     * The last time the photo album was updated.
     *
     * @return The last time the photo album was updated
     */
    public Date getUpdatedTime() {
        return mUpdatedTime;
    }

    /**
     * Determines whether the user can upload to the album and returns true if
     * the user owns the album, the album is not full, and the app can add
     * photos to the album. <br>
     * <br>
     * <b>Important</b> The privacy setting of the app should be at minimum as
     * the privacy setting of the album ({@link #getPrivacy()}.
     *
     * @return <code>True</code> if user can upload to this album
     */
    public boolean canUpload() {
        return mCanUpload;
    }

    public Bundle getBundle() {
        Bundle bundle = new Bundle();

        // add name
        if (mName != null) {
            bundle.putString(NAME, mName);
        }

        // add description
        if (mDescription != null) {
            bundle.putString(MESSAGE, mDescription);
        }

        // add privacy
        if (mPublishPrivacy != null) {
            bundle.putString(PRIVACY, mPublishPrivacy.getJSONString());
        }

        return bundle;
    }

    public Cover getCover() {
        return mCover;
    }

    /**
     * Builder for preparing the Album object to be published.
     */
    public static class Builder {
        private String mName = null;
        private String mMessage = null;
        private Privacy mPublishPrivacy = null;

        public Builder() {
        }

        /**
         * Add name to the album
         *
         * @param name
         *			The name of the album
         */
        public Builder setName(String name) {
            mName = name;
            return this;
        }

        /**
         * Add description to the album
         *
         * @param message
         *			The description of the album
         */
        public Builder setMessage(String message) {
            mMessage = message;
            return this;
        }

        /**
         * Add privacy setting to the photo
         *
         * @param privacy
         *			The privacy setting of the album
         * @see Privacy
         */
        public Builder setPrivacy(Privacy privacy) {
            mPublishPrivacy = privacy;
            return this;
        }

        public Album build() {
            return new Album(this);
        }
    }

    @Override
    public String getPath() {
        return GraphPath.ALBUMS;
    }

    @Override
    public Permission getPermission() {
        return Permission.PUBLISH_ACTION;
    }
}
