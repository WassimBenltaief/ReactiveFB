package com.beltaief.reactivefb.requests.photos;

import com.beltaief.reactivefb.SessionManager;
import com.beltaief.reactivefb.models.Photo;
import com.beltaief.reactivefb.requests.GetAction;
import com.beltaief.reactivefb.util.GraphPath;
import com.beltaief.reactivefb.util.Utils;
import com.facebook.GraphResponse;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class GetPhotosAction extends GetAction<List<Photo>> {

    public GetPhotosAction(SessionManager sessionManager) {
        super(sessionManager);
    }

    @Override
    protected String getGraphPath() {
        return getTarget() + "/" + GraphPath.PHOTOS;
    }

    @Override
    protected List<Photo> processResponse(GraphResponse response) {
        Utils.DataResult<Photo> dataResult = Utils.convert(response, new TypeToken<Utils.DataResult<Photo>>() {
        }.getType());
        return dataResult.data;
    }

}
