package com.beltaief.reactivefb.requests.photos;

import com.beltaief.reactivefb.SessionManager;
import com.beltaief.reactivefb.models.Photo;
import com.beltaief.reactivefb.requests.GetAction;
import com.beltaief.reactivefb.util.JsonUtils;
import com.facebook.GraphResponse;

public class GetPhotoAction extends GetAction<Photo> {

    public GetPhotoAction(SessionManager sessionManager) {
        super(sessionManager);
    }

    @Override
    protected String getGraphPath() {
        return getTarget();
    }

    @Override
    protected Photo processResponse(GraphResponse response) {
        return JsonUtils.fromJson(response.getRawResponse(), Photo.class);
    }

}
