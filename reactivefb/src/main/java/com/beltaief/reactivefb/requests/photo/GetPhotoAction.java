package com.beltaief.reactivefb.requests.photo;

/*
class GetPhotoAction extends GetAction<Photo> {

    private static String TAG = GetPhotoAction.class.getSimpleName();

    GetPhotoAction(SessionManager sessionManager) {
        super(sessionManager);
    }

    @Override
    protected String getGraphPath() {
        return getTarget();
    }

    @Override
    protected Photo processResponse(GraphResponse response) {
        Exception el;
        try {
            return JsonUtils.parsePhoto(response.getRawResponse());
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e(TAG, e.getMessage()+"");
            el = e;
        }
        throw new RuntimeException("Exception while serializing Photo.class :" + el.getMessage());
    }

}
*/
