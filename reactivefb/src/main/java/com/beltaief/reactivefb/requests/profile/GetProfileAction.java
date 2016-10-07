package com.beltaief.reactivefb.requests.profile;

/*
public class GetProfileAction extends GetAction<Profile> {

    private static final String TAG = GetProfileAction.class.getSimpleName();
    private Properties mProperties;

    public GetProfileAction(SessionManager sessionManager) {
        super(sessionManager);
    }

    public void setProperties(Properties properties) {
        mProperties = properties;
    }

    @Override
    protected String getGraphPath() {
        return getTarget();
    }

    @Override
    protected Bundle getBundle() {
        if (mProperties != null) {
            return mProperties.getBundle();
        }
        return null;
    }

    @Override
    protected Profile processResponse(GraphResponse response) {
        Exception el;
        try {
            return JsonUtils.parseProfile(response.getRawResponse());
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e(TAG, e.getMessage()+"");
            el = e;
        }
        throw new RuntimeException("Exception while serializing Profile.class :" + el.getMessage());

    }

}
*/
