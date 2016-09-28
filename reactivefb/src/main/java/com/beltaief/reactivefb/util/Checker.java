package com.beltaief.reactivefb.util;

/**
 * Created by wassim on 9/16/16.
 */
public final class Checker {

    public static <T> T checkNotNull(T value, String mesage) {
        if (value == null) {
            throw new NullPointerException(mesage);
        }
        return value;
    }
}
