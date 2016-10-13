package com.beltaief.reactivefb.requests;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.beltaief.reactivefb.ReactiveFB;
import com.beltaief.reactivefb.util.GraphPath;
import com.facebook.GraphResponse;

import io.reactivex.Single;

/**
 * Factory class for GraphRequest methods
 * Created by wassim on 9/16/16.
 */
public class ReactiveRequest {


    /**
     * Get the profile of the logged in user
     *
     * @return a single of GraphResponse representing a Profile model.
     */
    @NonNull
    public static Single<GraphResponse> getMe() {
        ReactiveFB.checkInit();
        // getProfile
        return Single.create(new RequestOnSubscribe(GraphPath.ME, null, null, 0));
    }

    /**
     * Get the profile of the logged in user
     *
     * @param fields string representing the fields that you would like to
     *               include in the response from the Graph API.
     * @return a single of GraphResponse representing a Profile Graph Object.
     */
    @NonNull
    public static Single<GraphResponse> getMe(String fields) {
        ReactiveFB.checkInit();
        // getProfile
        return Single.create(new RequestOnSubscribe(GraphPath.ME, null, fields, 0));
    }

    /**
     * Get the list of the friends of the logged in user
     *
     * @return a single of GraphResponse that represents a List of Profile Graph Object
     */
    @NonNull
    public static Single<GraphResponse> getFriends() {
        ReactiveFB.checkInit();
        // get friends
        return Single.create(new RequestOnSubscribe(GraphPath.ME, GraphPath.FRIENDS, null, 0));
    }

    /**
     * Get the list of the friends of the logged in user
     *
     * @param fields string representing the fields that you would like to
     *               include in the response from the Graph API.
     * @return a single of GraphResponse that represents a List of Profile Graph Object
     */
    @NonNull
    public static Single<GraphResponse> getFriends(String fields) {
        ReactiveFB.checkInit();
        // get friends
        return Single.create(new RequestOnSubscribe(GraphPath.ME, GraphPath.FRIENDS, fields, 0));
    }

    /**
     * Get the list of the friends of the logged in user
     *
     * @param fields string representing the fields that you would like to
     *               include in the response from the Graph API.
     * @param limit  limit the result to [0-n] number of items
     * @return a single of GraphResponse that represents a List of Profile Graph Object
     */
    @NonNull
    public static Single<GraphResponse> getFriends(String fields, int limit) {
        ReactiveFB.checkInit();
        // get friends
        return Single.create(new RequestOnSubscribe(GraphPath.ME, GraphPath.FRIENDS, fields, limit));
    }

    /**
     * Get a user profile by providing his facebookId
     *
     * @param profileId the facebookId of the Profile
     * @return a single of GraphResponse that represents a List of Profile Graph Object
     */
    @NonNull
    public static Single<GraphResponse> getProfile(@NonNull String profileId) {
        ReactiveFB.checkInit();
        // getProfile
        return Single.create(new RequestOnSubscribe(profileId, null, null, 0));
    }

    /**
     * Get a user profile by providing his facebookId
     *
     * @param fields    string representing the fields that you would like to
     *                  include in the response from the Graph API.
     * @param profileId the facebookId of the Profile
     * @return a single of GraphResponse that represents a List of Profile Graph Object
     */
    @NonNull
    public static Single<GraphResponse> getProfile(@Nullable String fields,
                                                   @NonNull String profileId) {
        ReactiveFB.checkInit();
        // getProfile
        return Single.create(new RequestOnSubscribe(profileId, null, fields, 0));
    }

    /**
     * Get a list of albums of the logged in user
     *
     * @return a single of GraphResponse that represents a List of Album Graph Object
     */
    @NonNull
    public static Single<GraphResponse> getMyAlbums() {
        ReactiveFB.checkInit();
        // getProfile
        return Single.create(new RequestOnSubscribe(GraphPath.ME, GraphPath.ALBUMS, null, 0));
    }

    /**
     * Get a list of albums of the logged in user
     *
     * @param fields string representing the fields that you would like to
     *               include in the response from the Graph API.
     * @return a single of GraphResponse that represents a List of Album Graph Object
     */
    @NonNull
    public static Single<GraphResponse> getMyAlbums(String fields) {
        ReactiveFB.checkInit();
        // getProfile
        return Single.create(new RequestOnSubscribe(GraphPath.ME, GraphPath.ALBUMS, fields, 0));
    }

    /**
     * Get a list of albums of the logged in user
     *
     * @param fields string representing the fields that you would like to
     *               include in the response from the Graph API.
     * @param limit  limit the result to [0-n] number of items
     * @return a single of GraphResponse that represents a List of Album Graph Object
     */
    @NonNull
    public static Single<GraphResponse> getMyAlbums(String fields, int limit) {
        ReactiveFB.checkInit();
        // getProfile
        return Single.create(new RequestOnSubscribe(GraphPath.ME, GraphPath.ALBUMS, fields, limit));
    }

    /**
     * Get a list of albums of a user
     *
     * @param userId the facebookId of the user
     * @return a single of GraphResponse that represents a List of Album Graph Object
     */
    @NonNull
    public static Single<GraphResponse> getAlbums(String userId) {
        ReactiveFB.checkInit();
        // getProfile
        return Single.create(new RequestOnSubscribe(userId, GraphPath.ALBUMS, null, 0));
    }

    /**
     * Get a list of albums of a user album
     *
     * @param userId the facebookId of the user
     * @param fields string representing the fields that you would like to
     *               include in the response from the Graph API.
     * @return a single of GraphResponse that represents a List of Album Graph Object
     */
    @NonNull
    public static Single<GraphResponse> getAlbums(String userId, String fields) {
        ReactiveFB.checkInit();
        // getProfile
        return Single.create(new RequestOnSubscribe(userId, GraphPath.ALBUMS, fields, 0));
    }

    /**
     * Get a list of albums of a user album
     *
     * @param userId the facebookId of the user
     * @param fields string representing the fields that you would like to
     *               include in the response from the Graph API.
     * @param limit  limit the result to [0-n] number of items
     * @return a single of GraphResponse that represents a List of Album Graph Object
     */
    @NonNull
    public static Single<GraphResponse> getAlbums(String userId, String fields, int limit) {
        ReactiveFB.checkInit();
        // getProfile
        return Single.create(new RequestOnSubscribe(userId, GraphPath.ALBUMS, fields, limit));
    }

    /**
     * Get a photo of a user, album, page, event ..
     *
     * @param photoId the id of the photo
     * @return a single of GraphResponse that represents a Photo Graph Object
     */
    @NonNull
    public static Single<GraphResponse> getPhoto(String photoId) {
        ReactiveFB.checkInit();
        // getProfile
        return Single.create(new RequestOnSubscribe(photoId, null, null, 0));
    }

    /**
     * Get a photo of a user, album, page, event ..
     *
     * @param photoId the id of the photo
     * @param fields  string representing the fields that you would like to
     *                include in the response from the Graph API.
     * @return a Single of GraphResponse that represents a Photo Graph Object
     */
    @NonNull
    public static Single<GraphResponse> getPhoto(String photoId, String fields) {
        ReactiveFB.checkInit();
        // getProfile
        return Single.create(new RequestOnSubscribe(photoId, null, fields, 0));
    }

    /**
     * Get list of photos of the logged in user
     *
     * @return a Single of GraphResponse that represents a List of Photo Graph Object
     */
    @NonNull
    public static Single<GraphResponse> getMyPhotos() {
        ReactiveFB.checkInit();
        // getProfile
        return Single.create(new RequestOnSubscribe(GraphPath.ME, GraphPath.PHOTOS, null, 0));
    }

    /**
     * Get list of photos of the logged in user
     *
     * @param fields string representing the fields that you would like to
     *               include in the response from the Graph API.
     * @return a Single of GraphResponse that represents a List of Photo Graph Object
     */
    @NonNull
    public static Single<GraphResponse> getMyPhotos(String fields) {
        ReactiveFB.checkInit();
        // getProfile
        return Single.create(new RequestOnSubscribe(GraphPath.ME, GraphPath.PHOTOS, fields, 0));
    }

    /**
     * Get list of photos of the logged in user
     *
     * @param fields string representing the fields that you would like to
     *               include in the response from the Graph API.
     * @param limit  limit the result to [0-n] number of items
     * @return a Single of GraphResponse that represents a List of Photo Graph Object
     */
    @NonNull
    public static Single<GraphResponse> getMyPhotos(String fields, int limit) {
        ReactiveFB.checkInit();
        // getProfile
        return Single.create(new RequestOnSubscribe(GraphPath.ME, GraphPath.PHOTOS, fields, limit));
    }

    /**
     * Get a user list of photos
     *
     * @param userId the facebookId of the user
     * @return a Single of GraphResponse that represents a List of Photo Graph Object
     */
    @NonNull
    public static Single<GraphResponse> getPhotos(String userId) {
        ReactiveFB.checkInit();
        // getProfile
        return Single.create(new RequestOnSubscribe(userId, GraphPath.PHOTOS, null, 0));
    }

    /**
     * Get a user list of photos
     *
     * @param userId the facebookId of the user*
     * @param fields string representing the fields that you would like to
     *               include in the response from the Graph API.
     * @return a Single of GraphResponse that represents a List of Photo Graph Object
     */
    @NonNull
    public static Single<GraphResponse> getPhotos(String userId, String fields) {
        ReactiveFB.checkInit();
        // getProfile
        return Single.create(new RequestOnSubscribe(userId, GraphPath.PHOTOS, fields, 0));
    }

    /**
     * Get a user list of photos
     *
     * @param userId the facebookId of the user*
     * @param fields string representing the fields that you would like to
     *               include in the response from the Graph API.
     * @param limit  limit the result to [0-n] number of items
     * @return a Single of GraphResponse that represents a List of Photo Graph Object
     */
    @NonNull
    public static Single<GraphResponse> getPhotos(String userId, String fields, int limit) {
        ReactiveFB.checkInit();
        // getProfile
        return Single.create(new RequestOnSubscribe(userId, GraphPath.PHOTOS, fields, limit));
    }
}
