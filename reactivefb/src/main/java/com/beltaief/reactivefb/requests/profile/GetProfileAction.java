package com.beltaief.reactivefb.requests.profile;

import android.os.Bundle;

import com.beltaief.reactivefb.SessionManager;
import com.beltaief.reactivefb.models.Profile;
import com.beltaief.reactivefb.models.Profile.Properties;
import com.beltaief.reactivefb.requests.GetAction;
import com.beltaief.reactivefb.util.JsonUtils;
import com.facebook.GraphResponse;

public class GetProfileAction extends GetAction<Profile> {

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
        return JsonUtils.fromJson(response.getRawResponse(), Profile.class);
    }

}
