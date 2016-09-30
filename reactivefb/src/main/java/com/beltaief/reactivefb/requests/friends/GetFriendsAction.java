package com.beltaief.reactivefb.requests.friends;

import android.os.Bundle;

import com.beltaief.reactivefb.SessionManager;
import com.beltaief.reactivefb.models.Profile;
import com.beltaief.reactivefb.models.Profile.Properties;
import com.beltaief.reactivefb.requests.common.GetAction;
import com.beltaief.reactivefb.util.GraphPath;
import com.beltaief.reactivefb.util.Utils;
import com.facebook.GraphResponse;
import com.google.gson.reflect.TypeToken;

import java.util.List;

class GetFriendsAction extends GetAction<List<Profile>> {

    private Properties mProperties;

    GetFriendsAction(SessionManager sessionManager) {
        super(sessionManager);
    }

    public void setProperties(Properties properties) {
        mProperties = properties;
    }

    @Override
    protected String getGraphPath() {
        return String.format("%s/%s", getTarget(), GraphPath.FRIENDS);
    }

    @Override
    protected Bundle getBundle() {
        if (mProperties != null) {
            return mProperties.getBundle();
        }
        return null;
    }

    @Override
    protected List<Profile> processResponse(GraphResponse response) {
        Utils.DataResult<Profile> dataResult = Utils.convert(response, new TypeToken<Utils.DataResult<Profile>>() {
        }.getType());
        return dataResult.data;
    }

}
