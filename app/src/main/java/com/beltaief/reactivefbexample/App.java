package com.beltaief.reactivefbexample;

import android.app.Application;
import com.beltaief.reactivefb.ReactiveFB;
import com.beltaief.reactivefb.SimpleFacebookConfiguration;
import com.beltaief.reactivefb.util.PermissionHelper;
import com.facebook.login.DefaultAudience;

/**
 * Created by wassim on 9/14/16.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // initialize facebook configuration
        PermissionHelper[] permissions = new PermissionHelper[]{
                PermissionHelper.USER_ABOUT_ME,
                PermissionHelper.EMAIL,
                PermissionHelper.USER_PHOTOS,
                PermissionHelper.USER_EVENTS,
                PermissionHelper.USER_ACTIONS_MUSIC,
                PermissionHelper.USER_FRIENDS,
                PermissionHelper.USER_GAMES_ACTIVITY,
                PermissionHelper.USER_BIRTHDAY,
                PermissionHelper.USER_TAGGED_PLACES,
                PermissionHelper.USER_MANAGED_GROUPS,
                PermissionHelper.PUBLISH_ACTION};

        SimpleFacebookConfiguration configuration = new SimpleFacebookConfiguration.Builder()
                .setAppId(String.valueOf(R.string.facebook_app_id))
                .setPermissions(permissions)
                .setDefaultAudience(DefaultAudience.FRIENDS)
                .setAskForAllPermissionsAtOnce(false)
                .build();

        // init lib
        ReactiveFB.sdkInitialize(this);
        ReactiveFB.setConfiguration(configuration);
    }
}
