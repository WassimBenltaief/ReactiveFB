package com.beltaief.reactivefb.requests.albums;

import android.util.Log;

import com.beltaief.reactivefb.SessionManager;
import com.beltaief.reactivefb.models.Album;
import com.beltaief.reactivefb.requests.common.GetAction;
import com.beltaief.reactivefb.util.GraphPath;
import com.bluelinelabs.logansquare.LoganSquare;
import com.facebook.GraphResponse;

import java.io.IOException;
import java.util.List;

class GetAlbumsAction extends GetAction<List<Album>> {

    private static final String TAG = GetAlbumsAction.class.getSimpleName();

    GetAlbumsAction(SessionManager sessionManager) {
        super(sessionManager);
    }

    @Override
    protected String getGraphPath() {
        return String.format("%s/%s", getTarget(), GraphPath.ALBUMS);
    }

    @Override
    protected List<Album> processResponse(GraphResponse response) {
        Exception el;
        try {
            return LoganSquare.parseList(response.getRawResponse(), Album.class);
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG, e.getMessage()+"");
            el = e;
        }
        throw new RuntimeException("Exception while serializing Album.class :" + el.getMessage());
    }

}
