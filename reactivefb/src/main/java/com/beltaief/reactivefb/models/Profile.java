package com.beltaief.reactivefb.models;

import android.os.Bundle;

import com.beltaief.reactivefb.util.Attributes;
import com.beltaief.reactivefb.util.Utils;
import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author wassim
 * // @see https://developers.facebook.com/docs/graph-api/reference/user
 */
@JsonObject
public class Profile extends User {

    @JsonField(name = Properties.FIRST_NAME)
    private String mFirstName;

    @JsonField(name = Properties.MIDDLE_NAME)
    private String mMiddleName;

    @JsonField(name = Properties.LAST_NAME)
    private String mLastName;

    @JsonField(name = Properties.GENDER)
    private String mGender;

    @JsonField(name = Properties.LOCALE)
    private String mLocale;

    @JsonField(name = Properties.LANGUAGE)
    private List<Language> mLanguages;

    @JsonField(name = Properties.LINK)
    private String mLink;

    @JsonField(name = Properties.AGE_RANGE)
    private AgeRange mAgeRange;

    @JsonField(name = Properties.THIRD_PARTY_ID)
    private String mThirdPartyId;

    @JsonField(name = Properties.INSTALLED)
    private Boolean mIsInstalled;

    @JsonField(name = Properties.TIMEZONE)
    private Integer mTimeZone;

    @JsonField(name = Properties.UPDATED_TIME)
    private Date mUpdatedTime;

    @JsonField(name = Properties.VERIFIED)
    private Boolean mVerified;

    @JsonField(name = Properties.BIO)
    private String mBio;

    @JsonField(name = Properties.BIRTHDAY)
    private String mBirthday;

    @JsonField(name = Properties.COVER)
    private Photo mCover;

    @JsonField(name = Properties.CURRENCY)
    private String mCurrency;

    @JsonField(name = Properties.EDUCATION)
    private List<Education> mEducation;

    @JsonField(name = Properties.EMAIL)
    private String mEmail;

    @JsonField(name = Properties.HOMETOWN)
    private IdName mHometown;

    @JsonField(name = Properties.LOCATION)
    private IdName mCurrentLocation;

    @JsonField(name = Properties.POLITICAL)
    private String mPolitical;

    @JsonField(name = Properties.FAVORITE_ATHLETES)
    private List<String> mFavoriteAthletes;

    @JsonField(name = Properties.FAVORITE_TEAMS)
    private List<String> mFavoriteTeams;

    @JsonField(name = Properties.PICTURE)
    private Utils.SingleDataResult<Image> mPicture;

    @JsonField(name = Properties.QUOTES)
    private String mQuotes;

    @JsonField(name = Properties.RELATIONSHIP_STATUS)
    private String mRelationshipStatus;

    @JsonField(name = Properties.RELIGION)
    private String mReligion;

    @JsonField(name = Properties.WEBSITE)
    private String mWebsite;

    @JsonField(name = Properties.WORK)
    private List<Work> mWorks;

    public Profile() {
    }

    /**
     * Returns the ID of the user. <br>
     * <br>
     * <b> Permissions:</b><br>
     * {@link Permission#USER_ABOUT_ME}
     *
     * @return the ID of the user
     */
    public String getId() {
        return super.getId();
    }

    /**
     * Returns the name of the user. <br>
     * <br>
     * <b> Permissions:</b><br>
     * {@link Permission#USER_ABOUT_ME}
     *
     * @return the name of the user
     */
    public String getName() {
        return super.getName();
    }

    /**
     * Returns the first name of the user. <br>
     * <br>
     * <b> Permissions:</b><br>
     * {@link Permission#USER_ABOUT_ME}
     *
     * @return the first name of the user
     */
    public String getFirstName() {
        return mFirstName;
    }

    /**
     * Returns the middle name of the user. <br>
     * <br>
     * <b> Permissions:</b><br>
     * {@link Permission#USER_ABOUT_ME}
     *
     * @return the middle name of the user
     */
    public String getMiddleName() {
        return mMiddleName;
    }

    /**
     * Returns the last name of the user. <br>
     * <br>
     * <b> Permissions:</b><br>
     * {@link Permission#USER_ABOUT_ME}
     *
     * @return the last name of the user
     */
    public String getLastName() {
        return mLastName;
    }

    /**
     * Returns the gender of the user. <br>
     * <br>
     * <b> Permissions:</b><br>
     * {@link Permission#USER_ABOUT_ME}
     *
     * @return the gender of the user
     */
    public String getGender() {
        return mGender;
    }

    /**
     * Return the ISO language code and ISO country code of the user. <br>
     * <br>
     * <b> Permissions:</b><br>
     * {@link Permission#USER_ABOUT_ME}
     *
     * @return the ISO language code and ISO country code of the user
     */
    public String getLocale() {
        return mLocale;
    }

    /**
     * Return the languages of the user.<br>
     * <br>
     * <b> Permissions:</b><br>
     * {@link Permission#USER_LIKES}
     *
     * @return the languages of the user
     */
    public List<Language> getLanguages() {
        return mLanguages;
    }

    /**
     * Returns the Facebook URL of the user. <br>
     * <br>
     * <b> Permissions:</b><br>
     * {@link Permission#USER_ABOUT_ME}
     *
     * @return the Facebook URL of the user
     */
    public String getLink() {
        return mLink;
    }

    /**
     * The user's age range. <br>
     * <br>
     * <b> Permissions:</b><br>
     * {@link Permission#USER_ABOUT_ME}
     *
     * @return the user's age range
     */
    public AgeRange getAgeRange() {
        return mAgeRange;
    }

    /**
     * An anonymous, but unique identifier for the user. <br>
     * <br>
     * <b> Permissions:</b><br>
     * {@link Permission#USER_ABOUT_ME}
     *
     * @return the an anonymous, but unique identifier for the user
     */
    public String getThirdPartyId() {
        return mThirdPartyId;
    }

    /**
     * Specifies whether the user has installed the application associated with
     * the app access token that is used to make the request. <br>
     * <br>
     * <b> Permissions:</b><br>
     * {@link Permission#USER_ABOUT_ME}
     *
     * @return <code>True</code> if installed, otherwise <code>False</code>
     */
    public Boolean getInstalled() {
        return mIsInstalled == null ? false : mIsInstalled;
    }

    /**
     * Return the timezone of the user.<br>
     * <br>
     * <b> Permissions:</b><br>
     * {@link Permission#USER_ABOUT_ME}
     *
     * <br>
     * <br>
     * <b>Note:</b> <br>
     * Avaliable only for my profile
     *
     * @return the timezone of the user
     */
    public Integer getTimeZone() {
        return mTimeZone;
    }

    /**
     * The last time the user's profile was updated; changes to the languages,
     * link, timezone, verified, interested_in, favorite_athletes,
     * favorite_teams, and video_upload_limits are not not reflected in this
     * value.<br>
     * <br>
     * <b> Permissions:</b><br>
     * {@link Permission#USER_ABOUT_ME}
     *
     * <br>
     * <br>
     *
     * @return string containing an ISO-8601 datetime
     */
    public Date getUpdatedTime() {
        return mUpdatedTime;
    }

    /**
     * The user's account verification status.<br>
     * <br>
     * <b> Permissions:</b><br>
     * {@link Permission#USER_ABOUT_ME}
     *
     * <br>
     * <br>
     * <b>Note:</b> <br>
     * A user is considered verified if she takes any of the following actions:
     *Registers for mobile
     * Confirms her account via SMS
     *
     * Enters a valid credit card
     * <br>
     * <br>
     *
     * @return The user's account verification status
     */
    public Boolean getVerified() {
        return mVerified;
    }

    /**
     * Return the biography of the user.<br>
     * <br>
     * <b> Permissions:</b><br>
     * {@link Permission#USER_ABOUT_ME}<br>
     *
     * @return the biography of the user
     */
    public String getBio() {
        return mBio;
    }

    /**
     * Returns the birthday of the user. <b>MM/DD/YYYY</b> format <br>
     * <br>
     * <b> Permissions:</b><br>
     * {@link Permission#USER_BIRTHDAY} <br>
     *
     * @return the birthday of the user
     */
    public String getBirthday() {
        return mBirthday;
    }

    /**
     * The user's cover photo. The url of cover will be under
     * {@link Photo#getSource()} <br>
     * <br>
     * <b> Permissions:</b><br>
     * {@link Permission#USER_ABOUT_ME}
     *
     * @return The user's cover photo
     */
    public Photo getCover() {
        return mCover;
    }

    /**
     * The user's currency settings <br>
     * <br>
     * <b> Permissions:</b><br>
     * {@link Permission#USER_ABOUT_ME}
     *
     * @return The user's currency settings
     */
    public String getCurrency() {
        return mCurrency;
    }

    /**
     * The user's education history <br>
     * <br>
     * <b> Permissions:</b><br>
     * {@link Permission#USER_EDUCATION_HISTORY}<br>
     *
     * @return The user's education history
     */
    public List<Education> getEducation() {
        return mEducation;
    }

    /**
     * Return the email of the user.<br>
     * <br>
     * <b> Permissions:</b> <br>
     * {@link Permission#EMAIL}
     * To get the details about the place, use GetPage with this id.
     * @return the email of the user
     */
    public String getEmail() {
        return mEmail;
    }

    /**
     * The user's hometown <br>
     * <br>
     * <b> Permissions:</b><br>
     * {@link Permission#USER_HOMETOWN}<br>
     * <br>
     * To get the details about the place, use GetPage with this id.
     * @return The page id and name of the Place set as user's hometown
     */
    public IdName getHometown() {
        return mHometown;
    }

    /**
     * The user's currently "living" location <br>
     * <br>
     * <b> Permissions:</b><br>
     * {@link Permission#USER_LOCATION}<br>
     *
     * @return The page id and name of the Place set as user's current location
     */
    public IdName getLocation() {
        return mCurrentLocation;
    }

    /**
     * The user's political view <br>
     * <br>
     * <b> Permissions:</b><br>
     * {@link Permission#USER_RELIGION_POLITICS}
     *
     * @return The user's political view
     */
    public String getPolitical() {
        return mPolitical;
    }

    /**
     * The user's favorite athletes <br>
     * <br>
     * <b> Permissions:</b><br>
     * {@link Permission#USER_LIKES}<br>
     *
     * @return The user's favorite athletes
     */
    public List<String> getFavoriteAthletes() {
        return mFavoriteAthletes;
    }

    /**
     * The user's favorite teams <br>
     * <br>
     * <b> Permissions:</b><br>
     * {@link Permission#USER_LIKES}<br>
     *
     * @return The user's favorite teams
     */
    public List<String> getFavoriteTeams() {
        return mFavoriteTeams;
    }

    /**
     * The user's profile pic <br>
     * <br>
     * <b> Permissions:</b><br>
     * {@link Permission#USER_ABOUT_ME}
     *
     * @return The user's profile pic
     */
    public String getPicture() {
        if (mPicture == null || mPicture.data == null) {
            return null;
        }
        return mPicture.data.getUrl();
    }

    /**
     * The user's favorite quotes <br>
     * <br>
     * <b> Permissions:</b><br>
     * {@link Permission#USER_ABOUT_ME}<br>
     *
     * @return The user's favorite quotes
     */
    public String getQuotes() {
        return mQuotes;
    }

    /**
     * The user's relationship status: <br>
     *Single
     * In a relationship
     * Engaged
     * Married
     *
     *It's complicated
     * In an open relationship
     * Widowed
     *
     *Separated
     * Divorced
     * In a civil union
     * In a
     * domestic partnership
     * <br>
     * <br>
     * <b> Permissions:</b><br>
     * {@link Permission#USER_RELATIONSHIPS}<br>
     *
     * @return The user's relationship status
     */
    public String getRelationshipStatus() {
        return mRelationshipStatus;
    }

    /**
     * The user's religion <br>
     * <br>
     * <b> Permissions:</b><br>
     * {@link Permission#USER_RELIGION_POLITICS}<br>
     *
     * @return The user's religion
     */
    public String getReligion() {
        return mReligion;
    }

    /**
     * The URL of the user's personal website <br>
     * <br>
     * <b> Permissions:</b><br>
     * {@link Permission#USER_WEBSITE}<br>
     *
     * @return The URL of the user's personal website
     */
    public String getWebsite() {
        return mWebsite;
    }

    /**
     * The user's work history <br>
     * <br>
     * <b> Permissions:</b><br>
     * {@link Permission#USER_WORK_HISTORY}<br>
     *
     * @return The user's work history
     */
    public List<Work> getWork() {
        return mWorks;
    }

    public static class Properties {
        private final Bundle mBundle;

        private Properties(Builder builder) {
            mBundle = new Bundle();
            Iterator<String> iterator = builder.properties.iterator();
            String fields = Utils.join(iterator, ",");
            mBundle.putString("fields", fields);
        }

        public Bundle getBundle() {
            return mBundle;
        }

        /**
         * <b>Description:</b><br>
         * The user's Facebook ID<br>
         * <br>
         *
         * <b>Permissions:</b><br>
         * {@link Permission#USER_ABOUT_ME}
         *
         */
        public static final String ID = "id";

        /**
         * <b>Description:</b><br>
         * The user's full name<br>
         * <br>
         *
         * <b>Permissions:</b><br>
         * {@link Permission#USER_ABOUT_ME}
         *
         */
        public static final String NAME = "name";

        /**
         * <b>Description:</b><br>
         * The user's first name<br>
         * <br>
         *
         * <b>Permissions:</b><br>
         * {@link Permission#USER_ABOUT_ME}
         *
         */
        public static final String FIRST_NAME = "first_name";

        /**
         * <b>Description:</b><br>
         * The user's middle name<br>
         * <br>
         *
         * <b>Permissions:</b><br>
         * {@link Permission#USER_ABOUT_ME}
         *
         */
        public static final String MIDDLE_NAME = "middle_name";

        /**
         * <b>Description:</b><br>
         * The user's last name<br>
         * <br>
         *
         * <b>Permissions:</b><br>
         * {@link Permission#USER_ABOUT_ME}
         *
         */
        public static final String LAST_NAME = "last_name";

        /**
         * <b>Description:</b><br>
         * The user's gender: female or male<br>
         * <br>
         *
         * <b>Permissions:</b><br>
         * {@link Permission#USER_ABOUT_ME}
         *
         */
        public static final String GENDER = "gender";

        /**
         * <b>Description:</b><br>
         * The user's locale<br>
         * <br>
         *
         * <b>Permissions:</b><br>
         * {@link Permission#USER_ABOUT_ME}
         *
         */
        public static final String LOCALE = "locale";

        /**
         * <b>Description:</b><br>
         * The user's languages<br>
         * <br>
         *
         * <b>Permissions:</b><br>
         * {@link Permission#USER_LIKES}
         *
         */
        public static final String LANGUAGE = "languages";

        /**
         * <b>Description:</b><br>
         * The URL of the profile for the user on Facebook<br>
         * <br>
         *
         * <b>Permissions:</b><br>
         * {@link Permission#USER_ABOUT_ME}
         *
         */
        public static final String LINK = "link";

        /**
         * <b>Description:</b><br>
         * The user's age range<br>
         * <br>
         *
         * <b>Permissions:</b><br>
         * {@link Permission#USER_ABOUT_ME}
         *
         */
        public static final String AGE_RANGE = "age_range";

        /**
         * <b>Description:</b><br>
         * An anonymous, but unique identifier for the user<br>
         * <br>
         *
         * <b>Permissions:</b><br>
         * {@link Permission#USER_ABOUT_ME}
         *
         */
        public static final String THIRD_PARTY_ID = "third_party_id";

        /**
         * <b>Description:</b><br>
         * Specifies whether the user has installed the application associated
         * with the app access token that is used to make the request<br>
         * <br>
         *
         * <b>Permissions:</b><br>
         * {@link Permission#USER_ABOUT_ME}
         *
         */
        public static final String INSTALLED = "installed";

        /**
         * <b>Description:</b><br>
         * The user's timezone offset from UTC<br>
         * <br>
         *
         * <b>Permissions:</b><br>
         * {@link Permission#USER_ABOUT_ME}
         *
         */
        public static final String TIMEZONE = "timezone";

        /**
         * <b>Description:</b><br>
         * The last time the user's profile was updated; changes to the
         * languages, link, timezone, verified, interested_in,
         * favorite_athletes, favorite_teams, and video_upload_limits are not
         * not reflected in this value<br>
         * <br>
         *
         * <b>Permissions:</b><br>
         * {@link Permission#USER_ABOUT_ME}
         *
         */
        public static final String UPDATED_TIME = "updated_time";

        /**
         * <b>Description:</b><br>
         * The user's account verification status, either true or false<br>
         * <br>
         *
         * <b>Permissions:</b><br>
         * {@link Permission#USER_ABOUT_ME}
         *
         */
        public static final String VERIFIED = "verified";

        /**
         * <b>Description:</b><br>
         * The user's biography<br>
         * <br>
         *
         * <b>Permissions:</b><br>
         * {@link Permission#USER_ABOUT_ME}
         *
         */
        public static final String BIO = "bio";

        /**
         * <b>Description:</b><br>
         * The user's birthday<br>
         * <br>
         *
         * <b>Permissions:</b><br>
         * {@link Permission#USER_BIRTHDAY}
         *
         */
        public static final String BIRTHDAY = "birthday";

        /**
         * <b>Description:</b><br>
         * The user's cover photo<br>
         * <br>
         *
         * <b>Permissions:</b><br>
         * {@link Permission#USER_ABOUT_ME}
         *
         */
        public static final String COVER = "cover";

        /**
         * <b>Description:</b><br>
         * The user's currency settings <br>
         * <br>
         *
         * <b>Permissions:</b><br>
         * {@link Permission#USER_ABOUT_ME}
         *
         */
        public static final String CURRENCY = "currency";

        /**
         * <b>Description:</b><br>
         * A list of the user's devices beyond desktop<br>
         * <br>
         *
         * <b>Permissions:</b><br>
         * {@link Permission#USER_ABOUT_ME}
         *
         */
        public static final String DEVICES = "devices";

        /**
         * <b>Description:</b><br>
         * A list of the user's education history<br>
         * <br>
         *
         * <b>Permissions:</b><br>
         * {@link Permission#USER_EDUCATION_HISTORY}
         *
         */
        public static final String EDUCATION = "education";

        /**
         * <b>Description:</b><br>
         * The email address granted by the user<br>
         * <br>
         *
         * <b>Note:</b> There is no way for apps to obtain email addresses for a
         * user's friends.<br>
         * <br>
         *
         * <b>Permissions:</b><br>
         * {@link Permission#EMAIL}
         */
        public static final String EMAIL = "email";

        /**
         * <b>Description:</b><br>
         * The user's hometown<br>
         * <br>
         *
         * <b>Permissions:</b><br>
         * {@link Permission#USER_HOMETOWN}
         *
         */
        public static final String HOMETOWN = "hometown";

        /**
         * <b>Description:</b><br>
         * The genders the user is interested in<br>
         * <br>
         *
         * <b>Permissions:</b><br>
         * {@link Permission#USER_RELATIONSHIP_DETAILS}
         *
         */
        public static final String INTERESTED_IN = "interested_in";

        /**
         * <b>Description:</b><br>
         * The user's current city<br>
         * <br>
         *
         * <b>Permissions:</b><br>
         * {@link Permission#USER_LOCATION}
         *
         */
        public static final String LOCATION = "location";

        /**
         * <b>Description:</b><br>
         * The user's political view<br>
         * <br>
         *
         * <b>Permissions:</b><br>
         * {@link Permission#USER_RELIGION_POLITICS}
         *
         */
        public static final String POLITICAL = "political";

        /**
         * <b>Description:</b><br>
         * The mobile payment price-points available for that user, for use when
         * processing payments using Facebook Credits<br>
         * <br>
         *
         * <b>Permissions:</b><br>
         * {@link Permission#USER_ABOUT_ME}
         *
         */
        public static final String PAYMENT_PRICEPOINTS = "payment_pricepoints";

        /**
         * <b>Description:</b><br>
         * The mobile payment price-points available for that user, for use when
         * processing payments using Local Currency<br>
         * <br>
         *
         * <b>Permissions:</b><br>
         * {@link Permission#USER_ABOUT_ME}
         *
         */
        public static final String PAYMENT_MOBILE_PRICEPOINTS = "payment_mobile_pricepoints";

        /**
         * <b>Description:</b><br>
         * The user's favorite athletes<br>
         * <br>
         *
         * <b>Permissions:</b><br>
         * {@link Permission#USER_LIKES}
         *
         */
        public static final String FAVORITE_ATHLETES = "favorite_athletes";

        /**
         * <b>Description:</b><br>
         * The user's favorite teams<br>
         * <br>
         *
         * <b>Permissions:</b><br>
         * {@link Permission#USER_LIKES}
         *
         */
        public static final String FAVORITE_TEAMS = "favorite_teams";

        /**
         * <b>Description:</b><br>
         * The user's profile pic<br>
         * <br>
         *
         * <b>Permissions:</b><br>
         * {@link Permission#USER_ABOUT_ME}
         *
         */
        public static final String PICTURE = "picture";

        /**
         * <b>Description:</b><br>
         * The user's favorite quotes<br>
         * <br>
         *
         * <b>Permissions:</b><br>
         * {@link Permission#USER_ABOUT_ME}
         *
         */
        public static final String QUOTES = "quotes";

        /**
         * <b>Description:</b><br>
         * The user's relationship status: Single, In a relationship, Engaged,
         * Married, It's complicated, In an open relationship, Widowed,
         * Separated, Divorced, In a civil union, In a domestic partnership<br>
         * <br>
         *
         * <b>Permissions:</b><br>
         * {@link Permission#USER_RELATIONSHIPS}
         *
         */
        public static final String RELATIONSHIP_STATUS = "relationship_status";

        /**
         * <b>Description:</b><br>
         * The user's religion<br>
         * <br>
         *
         * <b>Permissions:</b><br>
         * {@link Permission#USER_RELIGION_POLITICS}
         *
         */
        public static final String RELIGION = "religion";

        /**
         * <b>Description:</b><br>
         * Information about security settings enabled on the user's account<br>
         * <br>
         *
         * <b>Permissions:</b><br>
         * {@link Permission#USER_ABOUT_ME}
         *
         */
        public static final String SECURITY_SETTINGS = "security_settings";

        /**
         * <b>Description:</b><br>
         * The user's significant other<br>
         * <br>
         *
         * <b>Permissions:</b><br>
         * {@link Permission#USER_RELATIONSHIPS}
         *
         */
        public static final String SINGNIFICANT_OTHER = "significant_other";

        /**
         * <b>Description:</b><br>
         * The size of the video file and the length of the video that a user
         * can upload<br>
         * <br>
         *
         * <b>Permissions:</b><br>
         * {@link Permission#USER_ABOUT_ME}
         *
         */
        public static final String VIDEO_UPLOAD_LIMITS = "video_upload_limits";

        /**
         * <b>Description:</b><br>
         * The URL of the user's personal website<br>
         * <br>
         *
         * <b>Permissions:</b><br>
         * {@link Permission#USER_WEBSITE}
         *
         */
        public static final String WEBSITE = "website";

        /**
         * <b>Description:</b><br>
         * A list of the user's work history<br>
         * <br>
         *
         * <b>Permissions:</b><br>
         * {@link Permission#USER_WORK_HISTORY}
         *
         */
        public static final String WORK = "work";

        public static class Builder {
            Set<String> properties;

            public Builder() {
                properties = new HashSet<String>();
            }

            /**
             * Add property you need
             *
             * @param property
             *            The property of the user profile<br>
             *            For example: {@link Properties#FIRST_NAME}
             * @return {@link Builder}
             */
            public Builder add(String property) {
                properties.add(property);
                return this;
            }

            /**
             * Add property and attribute you need
             *
             * @param property
             *            The property of the user profile<br>
             *            For example: {@link Properties#PICTURE}
             * @param attributes
             *            For example: picture can have type,width and height<br>
             *
             * @return {@link Builder}
             */
            public Builder add(String property, Attributes attributes) {
                Map<String, String> map = attributes.getAttributes();
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(property);
                stringBuilder.append('.');
                stringBuilder.append(Utils.join(map, '.', '(', ')'));
                properties.add(stringBuilder.toString());
                return this;
            }

            public Properties build() {
                return new Properties(this);
            }

        }
    }

}
