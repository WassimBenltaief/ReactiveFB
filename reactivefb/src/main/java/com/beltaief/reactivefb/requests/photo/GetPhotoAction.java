package com.beltaief.reactivefb.requests.photo;

import android.util.Log;

import com.beltaief.reactivefb.SessionManager;
import com.beltaief.reactivefb.models.Photo;
import com.beltaief.reactivefb.requests.common.GetAction;
import com.beltaief.reactivefb.util.JsonUtils;
import com.facebook.GraphResponse;

import org.json.JSONException;

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
            return JsonUtils.parsePhoto(response.getRawResponse());
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e(TAG, e.getMessage()+"");
            el = e;
        }
        throw new RuntimeException("Exception while serializing Photo.class :" + el.getMessage());
    }

}
