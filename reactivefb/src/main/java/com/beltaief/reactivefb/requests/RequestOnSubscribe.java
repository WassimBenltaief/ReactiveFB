package com.beltaief.reactivefb.requests;

import android.support.annotation.Nullable;

import com.beltaief.reactivefb.util.Utils;
import com.facebook.GraphResponse;

import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;

/**
 * A custom class that extends SingleOnSubscribe.
 *
 * Created by wassim on 10/7/16.
 */
class RequestOnSubscribe implements SingleOnSubscribe<GraphResponse> {

    private final String mEdge;
    private final String mTarget;
    private final String mFields;
    private final int mLimit;

    /**
     * The constructor used to instantiate the SingleOnSubscribe from Single.create().
     * @param target the target in GraphAPI
     *               example : /v2.7/TARGET/albums
     * @param edge the edge in GraphAPI
     *             example : /v2.7/me/EDGE
     * @param fields the fields to get from the graphAPI
     *               example : /v2.7/me?FIELDS=id,name
     * @param limit the limit of the number of elements returned by the graphAPI
     *              example : /v2.7/me/albums?fields=count&limit=10
     */
    RequestOnSubscribe(@Nullable String target, @Nullable String edge,
                       @Nullable String fields, int limit) {
        this.mTarget = target;
        this.mEdge = edge;
        this.mFields = fields;
        this.mLimit = limit;
    }

    @Override
    public void subscribe(SingleEmitter<GraphResponse> emitter) throws Exception {
        RequestAction action = new RequestAction();
        action.setSingleEmitter(emitter);
        action.setTarget(mTarget);
        action.setEdge(mEdge);
        action.setBundle(Utils.getBundle(mFields, mLimit));
        action.execute();
    }
}
