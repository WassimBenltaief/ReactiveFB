package com.beltaief.reactivefb.requests.photo;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.beltaief.reactivefb.ReactiveFB;
import com.beltaief.reactivefb.models.Photo;
import com.beltaief.reactivefb.util.Utils;

import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;

/**
 * Created by wassim on 9/20/16.
 */

public class GetPhotoOnSubscribe implements SingleOnSubscribe<Photo> {

    private String photoId;
    private String bundleAsString;

    public GetPhotoOnSubscribe(@NonNull String id, @Nullable String bundle) {
        photoId = id;
        bundleAsString = bundle;
    }

    @Override
    public void subscribe(SingleEmitter<Photo> e) throws Exception {
        GetPhotoAction getPhotoAction = new GetPhotoAction(ReactiveFB.getSessionManager());
        getPhotoAction.setSingleEmitter(e);
        getPhotoAction.setTarget(photoId);
        getPhotoAction.setBundle(Utils.getBundle(bundleAsString));
        getPhotoAction.execute();
    }
}
