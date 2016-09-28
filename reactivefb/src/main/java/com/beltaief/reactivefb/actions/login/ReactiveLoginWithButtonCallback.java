package com.beltaief.reactivefb.actions.login;

import com.facebook.FacebookCallback;
import com.facebook.FacebookException;

import io.reactivex.ObservableEmitter;

/**
 * Created by wassim on 9/15/16.
 */
public class ReactiveLoginWithButtonCallback<T> implements FacebookCallback<T> {

    private ObservableEmitter<T> maybeEmitter;

    @Override
    public void onSuccess(T loginResult) {
        if(!maybeEmitter.isCancelled()) {
            maybeEmitter.onNext(loginResult);
        }
    }

    @Override
    public void onCancel() {
        if(!maybeEmitter.isCancelled()) {
            maybeEmitter.onError(new FacebookException("user canceled the operation"));
        }
    }

    @Override
    public void onError(FacebookException error) {
        if(!maybeEmitter.isCancelled()) {
            maybeEmitter.onError(new Throwable(error));
        }
    }

    public void setEmitter(ObservableEmitter<T> emitter) {
        this.maybeEmitter = emitter;
    }
}
