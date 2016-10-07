package com.beltaief.reactivefb.requests;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.beltaief.reactivefb.ReactiveFB;
import com.beltaief.reactivefb.util.GraphPath;
import com.facebook.GraphResponse;

import io.reactivex.Single;

/**
 * Created by wassim on 9/16/16.
 */
public class ReactiveRequest {


    @NonNull
    public static Single<GraphResponse> getMe() {
        ReactiveFB.checkInit();
        // getProfile
        return Single.create(new RequestOnSubscribe(GraphPath.ME, null, null, 0));
    }

    @NonNull
    public static Single<GraphResponse> getMe(String fields) {
        ReactiveFB.checkInit();
        // getProfile
        return Single.create(new RequestOnSubscribe(GraphPath.ME, null, fields, 0));
    }



    @NonNull
    public static Single<GraphResponse> getFriends() {
        ReactiveFB.checkInit();
        // get friends
        return Single.create(new RequestOnSubscribe(GraphPath.ME, GraphPath.FRIENDS, null, 0));
    }

    @NonNull
    public static Single<GraphResponse> getFriends(String fields) {
        ReactiveFB.checkInit();
        // get friends
        return Single.create(new RequestOnSubscribe(GraphPath.ME, GraphPath.FRIENDS, fields, 0));
    }

    @NonNull
    public static Single<GraphResponse> getFriends(String fields, int limit) {
        ReactiveFB.checkInit();
        // get friends
        return Single.create(new RequestOnSubscribe(GraphPath.ME, GraphPath.FRIENDS, fields, limit));
    }



    @NonNull
    public static Single<GraphResponse> getProfile(@NonNull String profileId) {
        ReactiveFB.checkInit();
        // getProfile
        return Single.create(new RequestOnSubscribe(profileId, null, null, 0));
    }

    @NonNull
    public static Single<GraphResponse> getProfile(@Nullable String fields,
                                             @NonNull String profileId) {
        ReactiveFB.checkInit();
        // getProfile
        return Single.create(new RequestOnSubscribe(profileId, null, fields, 0));
    }


    @NonNull
    public static Single<GraphResponse> getMyAlbums(){
        ReactiveFB.checkInit();
        // getProfile
        return Single.create(new RequestOnSubscribe(GraphPath.ME, GraphPath.ALBUMS, null, 0));
    }

    @NonNull
    public static Single<GraphResponse> getMyAlbums(String fields){
        ReactiveFB.checkInit();
        // getProfile
        return Single.create(new RequestOnSubscribe(GraphPath.ME, GraphPath.ALBUMS, fields, 0));
    }

    @NonNull
    public static Single<GraphResponse> getMyAlbums(String fields, int limit){
        ReactiveFB.checkInit();
        // getProfile
        return Single.create(new RequestOnSubscribe(GraphPath.ME, GraphPath.ALBUMS, fields, limit));
    }

    @NonNull
    public static Single<GraphResponse> getUserAlbums(String userId){
        ReactiveFB.checkInit();
        // getProfile
        return Single.create(new RequestOnSubscribe(userId, GraphPath.ALBUMS, null, 0));
    }

    @NonNull
    public static Single<GraphResponse> getUserAlbums(String userId, String fields){
        ReactiveFB.checkInit();
        // getProfile
        return Single.create(new RequestOnSubscribe(userId, GraphPath.ALBUMS, fields, 0));
    }

    @NonNull
    public static Single<GraphResponse> getUserAlbums(String userId, String fields, int limit){
        ReactiveFB.checkInit();
        // getProfile
        return Single.create(new RequestOnSubscribe(userId, GraphPath.ALBUMS, fields, limit));
    }

    @NonNull
    public static Single<GraphResponse> getPhoto(String photoId) {
        ReactiveFB.checkInit();
        // getProfile
        return Single.create(new RequestOnSubscribe(photoId, null, null, 0));
    }

    @NonNull
    public static Single<GraphResponse> getPhoto(String photoId, String fields) {
        ReactiveFB.checkInit();
        // getProfile
        return Single.create(new RequestOnSubscribe(photoId, null, fields, 0));
    }
}
