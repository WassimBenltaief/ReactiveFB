package com.beltaief.reactivefb.requests.profile;

import android.os.Bundle;
import android.util.Log;

import com.beltaief.reactivefb.SessionManager;
import com.beltaief.reactivefb.models.Profile;
import com.beltaief.reactivefb.models.Profile.Properties;
import com.beltaief.reactivefb.requests.common.GetAction;
import com.bluelinelabs.logansquare.LoganSquare;
import com.facebook.GraphResponse;

import java.io.IOException;


public class GetProfileAction extends GetAction<Profile> {

    private static final String TAG = GetProfileAction.class.getSimpleName();
    private Properties mProperties;

    public GetProfileAction(SessionManager sessionManager) {
        super(sessionManager);
    }

    public void setProperties(Properties properties) {
        mProperties = properties;
    }

    @Override
    protected String getGraphPath() {
        return getTarget();
    }

    @Override
    protected Bundle getBundle() {
        if (mProperties != null) {
            return mProperties.getBundle();
        }
        return null;
    }

    @Override
    protected Profile processResponse(GraphResponse response) {
        Exception el;
        try {
            return LoganSquare.parse(response.getRawResponse(), Profile.class);
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG, e.getMessage()+"");
            el = e;
        }
        throw new RuntimeException("Exception while serializing Profile.class :" + el.getMessage());

    }

}
