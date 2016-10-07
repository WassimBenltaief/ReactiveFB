package com.beltaief.reactivefb.requests;

import android.support.annotation.Nullable;

import com.beltaief.reactivefb.util.Utils;
import com.facebook.GraphResponse;

import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;

/**
 * Created by wassim on 10/7/16.
 */

public class RequestOnSubscribe implements SingleOnSubscribe<GraphResponse> {

    private final String mEdge;
    private final String mTarget;
    private final String mFields;
    private final int mLimit;

    public RequestOnSubscribe(@Nullable String target, @Nullable String edge,
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
