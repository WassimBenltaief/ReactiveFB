package com.beltaief.reactivefb.requests.photos;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.beltaief.reactivefb.ReactiveFB;
import com.beltaief.reactivefb.models.Photo;

import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;

/**
 * Created by wassim on 9/20/16.
 */

public class GetPhotoOnSubscribe implements SingleOnSubscribe<Photo> {

    private String photoId;

    public GetPhotoOnSubscribe(@NonNull String id) {
        photoId = id;
    }

    @Override
    public void subscribe(SingleEmitter<Photo> e) throws Exception {
        get(photoId, e);
    }

    private void get(String photoId, SingleEmitter<Photo> emitter) {
        GetPhotoAction getPhotoAction = new GetPhotoAction(ReactiveFB.getSessionManager());
        getPhotoAction.setSingleEmitter(emitter);
        getPhotoAction.setTarget(photoId);
        getPhotoAction.setBundle(getBundle());
        getPhotoAction.execute();
    }

    public Bundle getBundle() {
        Bundle bundle = new Bundle();
        bundle.putString("fields", "album,images");
        return bundle;
    }
}
