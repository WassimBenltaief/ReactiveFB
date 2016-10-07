package com.beltaief.reactivefbexample.util;

import com.facebook.GraphResponse;
import com.google.gson.Gson;

import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;

/**
 * Created by wassim on 10/7/16.
 */

public class JsonTransformer<K> implements Function<Single<GraphResponse>, SingleSource<K>> {

    private final Gson mGson;
    private final Class mClazz;

    public JsonTransformer(Class clazz) {
        this.mGson = new Gson();
        this.mClazz = clazz;
    }

    @Override
    public SingleSource<K> apply(Single<GraphResponse> tSingle) throws Exception {
        return tSingle.map(new Function<GraphResponse, K>() {
            @Override
            public K apply(GraphResponse graphResponse) throws Exception {
                return (K) mGson.fromJson(graphResponse.getRawResponse(), mClazz);
            }
        });
    }
}
