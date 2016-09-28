package com.beltaief.reactivefb.requests;

public interface OnErrorListener {

    void onException(Throwable throwable);

    void onFail(String reason);
}