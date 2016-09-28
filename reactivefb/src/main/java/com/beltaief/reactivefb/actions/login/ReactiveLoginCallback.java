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
    public boolean askPublishPermissions;
    public List<String> publishPermissions;

    @Override
    public void onSuccess(T loginResult) {
        if(!maybeEmitter.isCancelled()) {
            maybeEmitter.onSuccess(loginResult);
        }
    }

    @Override
    public void onCancel() {
        if(!maybeEmitter.isCancelled()) {
            maybeEmitter.onComplete();
        }
    }

    @Override
    public void onError(FacebookException error) {
        if(!maybeEmitter.isCancelled()) {
            maybeEmitter.onError(new Throwable(error));
        }
    }

    public void setEmitter(MaybeEmitter<T> emitter) {
        this.maybeEmitter = emitter;
    }
}
