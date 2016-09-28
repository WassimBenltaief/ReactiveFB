package com.beltaief.reactivefb;

import android.content.Context;

import com.beltaief.reactivefb.models.Permission;
import com.facebook.AccessToken;
import com.facebook.FacebookSdk;

/**
 * Created by wassim on 9/15/16.
 */
public class ReactiveFB {

    private static ReactiveFB mInstance = null;
    private static SimpleFacebookConfiguration mConfiguration = new SimpleFacebookConfiguration.Builder().build();
    private static SessionManager mSessionManager = null;

    public static void sdkInitialize(Context context){
        if (mInstance == null) {
            Class clazz = ReactiveFB.class;
            synchronized (clazz) {
                mInstance = new ReactiveFB();
            }
            FacebookSdk.sdkInitialize(context);
            mSessionManager = new SessionManager(mConfiguration);
        }
    }

    public static void checkInit() {
        if(!FacebookSdk.isInitialized()){
            throw new RuntimeException("ReactiveFB not initialized. Are you missing " +
                    "ReactiveFB.sdkInitialize(context) ?");
        }
    }

    /**
     * Get Session Manager
     *
     * @return
     */
    public static SessionManager getSessionManager() {
        return mSessionManager;
    }

    /**
     * Get configuration
     *
     * @return
     */
    public static SimpleFacebookConfiguration getConfiguration() {
        return mConfiguration;
    }

    public static void setConfiguration(SimpleFacebookConfiguration configuration) {
        mConfiguration = configuration;
    }

    public static boolean checkPermission(Permission permission) {
        return AccessToken.getCurrentAccessToken().getPermissions().contains(permission.getValue());
    }
}
