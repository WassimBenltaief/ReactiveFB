package com.beltaief.reactivefbexample.util;

import com.facebook.GraphResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * Created by wassim on 10/7/16.
 */

public class JsonListTransformer<K> implements Function<Single<GraphResponse>, SingleSource<K>> {

    private final Gson mGson;
    private final Class mClazz;

    public JsonListTransformer(Class clazz) {
        this.mGson = new Gson();
        this.mClazz = clazz;
    }

    @Override
    public SingleSource<K> apply(Single<GraphResponse> tSingle) throws Exception {
        return tSingle.map(new Function<GraphResponse, K>() {
            @Override
            public K apply(GraphResponse graphResponse) throws Exception {
                Type listType = new TypeToken<ClassUtil<? super T>>(){}.getType();

                return (K) mGson.fromJson(graphResponse.getRawResponse(), listType);
            }
        });
    }

    class ClassUtil<K> {

        List<? extends K> list;
    }
}
