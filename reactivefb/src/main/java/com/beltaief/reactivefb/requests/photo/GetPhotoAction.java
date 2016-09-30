package com.beltaief.reactivefb.requests.photo;

import android.util.Log;

import com.beltaief.reactivefb.SessionManager;
import com.beltaief.reactivefb.models.Photo;
import com.beltaief.reactivefb.requests.common.GetAction;
import com.bluelinelabs.logansquare.LoganSquare;
import com.facebook.GraphResponse;

import java.io.IOException;

class GetPhotoAction extends GetAction<Photo> {

    private static String TAG = GetPhotoAction.class.getSimpleName();

    GetPhotoAction(SessionManager sessionManager) {
        super(sessionManager);
    }

    @Override
    protected String getGraphPath() {
        return getTarget();
    }

    @Override
    protected Photo processResponse(GraphResponse response) {
        Exception el;
        try {
            return LoganSquare.parse(response.getRawResponse(), Photo.class);
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG, e.getMessage()+"");
            el = e;
        }
        throw new RuntimeException("Exception while serializing Photo.class :" + el.getMessage());
    }

}
