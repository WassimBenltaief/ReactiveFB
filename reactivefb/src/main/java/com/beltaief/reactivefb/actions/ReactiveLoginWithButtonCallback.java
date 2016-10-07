package com.beltaief.reactivefb.actions;

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
        maybeEmitter.onNext(loginResult);
    }

    @Override
    public void onCancel() {
        maybeEmitter.onError(new FacebookException("user canceled the operation"));
    }

    @Override
    public void onError(FacebookException error) {
        maybeEmitter.onError(new Throwable(error));
    }

    public void setEmitter(ObservableEmitter<T> emitter) {
        this.maybeEmitter = emitter;
    }
}
