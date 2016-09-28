package com.beltaief.reactivefb.requests;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.beltaief.reactivefb.ReactiveFB;
import com.beltaief.reactivefb.models.Album;
import com.beltaief.reactivefb.models.Photo;
import com.beltaief.reactivefb.models.Profile;
import com.beltaief.reactivefb.requests.albums.GetAlbumsOnSubscribe;
import com.beltaief.reactivefb.requests.friends.GetFriendsOnSubscribe;
import com.beltaief.reactivefb.requests.photos.GetPhotoOnSubscribe;
import com.beltaief.reactivefb.requests.profile.GetProfileOnSubscribe;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by wassim on 9/16/16.
 */
public class ReactiveRequest {

    @NonNull
    public static Single<Profile> getCurrentProfile(@Nullable String bundle) {
        ReactiveFB.checkInit();
        // getProfile
        return Single.create(new GetProfileOnSubscribe(bundle, null));
    }

    @NonNull
    public static Single<List<Profile>> getFriends(String bundle) {
        ReactiveFB.checkInit();
        // get friends
        return Single.create(new GetFriendsOnSubscribe(bundle));
    }

    @NonNull
    public static Single<Profile> getProfileById(@Nullable String bundle,
                                             @NonNull String profileId) {
        ReactiveFB.checkInit();
        // getProfile
        return Single.create(new GetProfileOnSubscribe(bundle, profileId));
    }

    @NonNull
    public static Single<List<Album>> getAlbums(){
        ReactiveFB.checkInit();
        // getProfile
        return Single.create(new GetAlbumsOnSubscribe());
    }

    @NonNull
    public static Single<Photo> getPhoto(String id) {
        ReactiveFB.checkInit();
        // getProfile
        return Single.create(new GetPhotoOnSubscribe(id));
    }
}
