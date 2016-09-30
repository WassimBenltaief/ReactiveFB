package com.beltaief.reactivefb.requests.albums;

import com.beltaief.reactivefb.SessionManager;
import com.beltaief.reactivefb.models.Album;
import com.beltaief.reactivefb.requests.common.GetAction;
import com.beltaief.reactivefb.util.GraphPath;
import com.beltaief.reactivefb.util.Utils;
import com.facebook.GraphResponse;
import com.google.gson.reflect.TypeToken;

import java.util.List;

class GetAlbumsAction extends GetAction<List<Album>> {

    GetAlbumsAction(SessionManager sessionManager) {
        super(sessionManager);
    }

    @Override
    protected String getGraphPath() {
        return String.format("%s/%s", getTarget(), GraphPath.ALBUMS);
    }

    @Override
    protected List<Album> processResponse(GraphResponse response) {
        Utils.DataResult<Album> dataResult = Utils.convert(response, new TypeToken<Utils.DataResult<Album>>() {
        }.getType());
        return dataResult.data;
    }

}
