package com.beltaief.reactivefb.requests.albums;

import com.beltaief.reactivefb.ReactiveFB;
import com.beltaief.reactivefb.models.Album;
import com.beltaief.reactivefb.util.Utils;

import java.util.List;

import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;

/**
 * Created by wassim on 9/20/16.
 */

public class GetAlbumsOnSubscribe implements SingleOnSubscribe<List<Album>> {

    private String bundleAsString;

    public GetAlbumsOnSubscribe(String bundle) {
        bundleAsString = bundle;
    }

    @Override
    public void subscribe(SingleEmitter<List<Album>> e) throws Exception {

        GetAlbumsAction getAction = new GetAlbumsAction(ReactiveFB.getSessionManager());
        getAction.setSingleEmitter(e);
        getAction.setBundle(Utils.getBundle(bundleAsString));
        getAction.execute();
    }
}
