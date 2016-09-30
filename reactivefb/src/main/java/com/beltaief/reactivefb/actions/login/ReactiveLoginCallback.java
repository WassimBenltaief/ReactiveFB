package com.beltaief.reactivefb.actions.login;

import com.facebook.FacebookCallback;
import com.facebook.FacebookException;

import java.util.List;

import io.reactivex.MaybeEmitter;

/**
 * Created by wassim on 9/15/16.
 */
public class ReactiveLoginCallback<T> implements FacebookCallback<T> {

    private static final String TAG = ReactiveLoginCallback.class.getSimpleName();
    private MaybeEmitter<T> maybeEmitter;
    boolean askPublishPermissions;
    List<String> publishPermissions;

    @Override
    public void onSuccess(T loginResult) {
        maybeEmitter.onSuccess(loginResult);
    }

    @Override
    public void onCancel() {
        maybeEmitter.onComplete();
    }

    @Override
    public void onError(FacebookException error) {
        maybeEmitter.onError(new Throwable(error));
    }

    public void setEmitter(MaybeEmitter<T> emitter) {
        this.maybeEmitter = emitter;
    }
}
