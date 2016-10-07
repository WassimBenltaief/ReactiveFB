package com.beltaief.reactivefb.requests.albums;

/**
 * Created by wassim on 9/20/16.
 */

/*
public class GetAlbumsOnSubscribe implements SingleOnSubscribe<GraphRequest> {

    private String bundleAsString;

    public GetAlbumsOnSubscribe(String bundle) {
        bundleAsString = bundle;
    }

    @Override
    public void subscribe(SingleEmitter<GraphRequest> emitter) throws Exception {
        GetAlbumsAction getAction = new GetAlbumsAction(ReactiveFB.getSessionManager());
        getAction.setTarget(GraphPath.ALBUMS);
        getAction.setSingleEmitter(emitter);
        getAction.setBundle(Utils.getBundle(bundleAsString, mLimit));
        getAction.execute();
    }
}
*/
