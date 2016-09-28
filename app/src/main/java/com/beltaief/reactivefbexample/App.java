package com.beltaief.reactivefbexample;

import android.app.Application;

import com.beltaief.reactivefb.ReactiveFB;
import com.beltaief.reactivefb.SimpleFacebookConfiguration;
import com.beltaief.reactivefb.models.Permission;
import com.facebook.login.DefaultAudience;

/**
 * Created by wassim on 9/14/16.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // initialize facebook configuration
        Permission[] permissions = new Permission[]{
                // Permission.PUBLIC_PROFILE,
                Permission.EMAIL,
                Permission.USER_PHOTOS,
                Permission.USER_EVENTS,
                Permission.USER_ACTIONS_MUSIC,
                Permission.USER_FRIENDS,
                Permission.USER_GAMES_ACTIVITY,
                Permission.USER_BIRTHDAY,
                Permission.USER_TAGGED_PLACES,
                Permission.USER_MANAGED_GROUPS,
                Permission.PUBLISH_ACTION};

        SimpleFacebookConfiguration configuration = new SimpleFacebookConfiguration.Builder()
                .setAppId(String.valueOf(R.string.facebook_app_id))
                .setPermissions(permissions)
                .setDefaultAudience(DefaultAudience.FRIENDS)
                .setAskForAllPermissionsAtOnce(false)
                //.setGraphVersion("v2.7")
                .build();

        // init lib
        ReactiveFB.sdkInitialize(this);
        ReactiveFB.setConfiguration(configuration);
    }
}
