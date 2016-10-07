package com.beltaief.reactivefb.requests.friends;

/*class GetFriendsAction extends GetAction<List<Profile>> {

    private static final String TAG = GetFriendsAction.class.getSimpleName();
    private Properties mProperties;

    GetFriendsAction(SessionManager sessionManager) {
        super(sessionManager);
    }

    @Override
    protected String getGraphPath() {
        return String.format("%s/%s", getTarget(), GraphPath.FRIENDS);
    }

    @Override
    protected Bundle getBundle() {
        if (mProperties != null) {
            return mProperties.getBundle();
        }
        return null;
    }

    @Override
    protected List<Profile> processResponse(GraphResponse response) {
        Exception el;
        try {
            return JsonUtils.parseFriends(response.getRawResponse());
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e(TAG, e.getMessage()+"");
            el = e;
        }
        throw new RuntimeException("Exception while serializing a list of Profile.class :" + el.getMessage());
    }

}*/
