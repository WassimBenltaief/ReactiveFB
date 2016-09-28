package com.beltaief.reactivefbexample.util;

import android.app.Activity;

public class Example {

    private final String mTitle;
    private final Class<? extends Activity> aActivity;
    private final boolean mRequiresLogin;

    public Example(String title, Class<? extends Activity> clazz, boolean requiresLogin) {
        mTitle = title;
        aActivity = clazz;
        mRequiresLogin = requiresLogin;
    }

    public String getTitle() {
        return mTitle;
    }

    public Class<? extends Activity> getActivity() {
        return aActivity;
    }

    public boolean isRequireLogin() {
        return mRequiresLogin;
    }

}
