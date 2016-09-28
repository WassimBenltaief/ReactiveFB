package com.beltaief.reactivefb.requests.albums;

import android.os.Bundle;

import com.beltaief.reactivefb.ReactiveFB;
import com.beltaief.reactivefb.models.Album;

import java.util.List;

import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;

/**
 * Created by wassim on 9/20/16.
 */

public class GetAlbumsOnSubscribe implements SingleOnSubscribe<List<Album>> {

    public GetAlbumsOnSubscribe() {
    }

    @Override
    public void subscribe(SingleEmitter<List<Album>> e) throws Exception {
        getAlbums(e);
    }

    private void getAlbums(SingleEmitter<List<Album>> singleEmitter) {
        GetAlbumsAction getAlbumsAction = new GetAlbumsAction(ReactiveFB.getSessionManager());
        getAlbumsAction.setSingleEmitter(singleEmitter);
        getAlbumsAction.setBundle(getBundle());
        getAlbumsAction.execute();
    }

    private Bundle getBundle(){
        Bundle bundle = new Bundle();
        bundle.putString("fields", "cover_photo,description,created_time,count");
        return bundle;
    }
}
