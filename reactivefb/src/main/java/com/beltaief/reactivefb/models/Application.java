package com.beltaief.reactivefb.models;

/**
 * Object that describes 'Application' on facebook side.
 *
 * @author sromku
 */

public class Application {

    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String NAMESPACE = "namespace";


    private String mAppId = null;

    private String mAppName = null;

    private String mAppNamespace = null;

    /**
     * @return Application id
     */
    public String getAppId() {
        return mAppId;
    }

    /**
     * @return Application name
     */
    public String getAppName() {
        return mAppName;
    }

    /**
     * @return Application namespace
     */
    public String getAppNamespace() {
        return mAppNamespace;
    }
}
