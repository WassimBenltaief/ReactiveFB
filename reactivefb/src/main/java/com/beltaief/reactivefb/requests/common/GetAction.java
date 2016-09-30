package com.beltaief.reactivefb.requests.common;

import android.os.Bundle;

import com.beltaief.reactivefb.SessionManager;
import com.beltaief.reactivefb.util.Errors;
import com.beltaief.reactivefb.util.Logger;
import com.beltaief.reactivefb.util.Utils;
import com.facebook.AccessToken;
import com.facebook.FacebookAuthorizationException;
import com.facebook.FacebookGraphResponseException;
import com.facebook.FacebookRequestError;
import com.facebook.GraphRequest;
import com.facebook.GraphRequestAsyncTask;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;

import org.json.JSONException;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import io.reactivex.SingleEmitter;

public class GetAction<T> extends AbstractAction {

    private String mTarget = "me"; // default
    private String mEdge = null;
    private SingleEmitter<T> mSingleEmitter = null;
    private Cursor<T> mCursor = null;
    private Bundle bundle;

    private GraphRequest.Callback mCallback = new GraphRequest.Callback() {
        @Override
        public void onCompleted(GraphResponse response) {
            FacebookRequestError error = response.getError();
            if (error != null) {
                Logger.logError(GetAction.class,
                        "Failed to get what you have requested", error.getException());

                if (mSingleEmitter != null) {
                        mSingleEmitter.onError(error.getException());
                }
            } else {
                if (response.getRawResponse() == null) {
                    Logger.logError(GetAction.class, "The response GraphObject " +
                            "has null value. Response=" + response.toString(), null);
                    mSingleEmitter.onError(new FacebookGraphResponseException(response,
                            "The response has null value"));
                } else {
                    if (mSingleEmitter != null) {
                        T result = null;
                        try {
                            updateCursor(response);
                            result = processResponse(response);
                        } catch (Exception e) {
                            mSingleEmitter.onError(e);
                            return;
                        }
                        mSingleEmitter.onSuccess(result);
                    }
                }
            }
        }
    };

    public GetAction(SessionManager sessionManager) {
        super(sessionManager);
    }

    public void setEdge(String edge) {
        mEdge = edge;
    }

    public void setTarget(String target) {
        if (target != null) {
            mTarget = target;
        }
    }

    public void setSingleEmitter(SingleEmitter<T> singleEmitter) {
        mSingleEmitter = singleEmitter;
    }

    @Override
    protected void executeImpl() {
        if (sessionManager.isLoggedIn()) {
            AccessToken accessToken = sessionManager.getAccessToken();
            Bundle bundle = updateAppSecretProof();
            GraphRequest request = new GraphRequest(accessToken, getGraphPath(),
                    bundle, HttpMethod.GET);
            request.setVersion(configuration.getGraphVersion());
            runRequest(request);
        } else {
            String reason = Errors.getError(Errors.ErrorMsg.LOGIN);
            Logger.logError(getClass(), reason, null);
            if (mSingleEmitter != null) {
                mSingleEmitter.onError(new FacebookAuthorizationException(reason));
            }
        }
    }

    /**
     * Update the params to contain 'appsecret_proof' if it was asked in
     * SimpleFacebookConfiguration.
     * @return Updated bundle
     * // @see https://developers.facebook.com/docs/graph-api/securing-requests
     */
    private Bundle updateAppSecretProof() {
        if (configuration.useAppsecretProof()) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putString("appsecret_proof", Utils.encode(configuration.getAppSecret(),
                    sessionManager.getAccessToken().getToken()));
        }
        return bundle;
    }

    protected String getTarget() {
        return mTarget;
    }

    protected String getGraphPath() {
        if (mEdge != null) {
            return mTarget + "/" + mEdge;
        }
        return mTarget;
    }

    protected Bundle getBundle() {
        return null;
    }

    /**
     * It is better to override this method and implement your faster
     * conversion.
     */
    protected T processResponse(GraphResponse response) {
        Type type = mSingleEmitter.getClass().getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            Object arrayJson;
            try {
                arrayJson = response.getJSONObject().get("data");
            } catch (JSONException e) {
                return null;
            }
            T data = Utils.convert(String.valueOf(arrayJson), type);
            return data;
        }
        return Utils.convert(response, type);
    }

    void runRequest(GraphRequest request) {
        request.setCallback(mCallback);
        GraphRequestAsyncTask task = new GraphRequestAsyncTask(request);
        task.execute();
    }

    /**
     * set next and prev pages requests
     *
     * @param response
     */
    private void updateCursor(GraphResponse response) {
        if (mSingleEmitter == null) {
            return;
        }

        if (mCursor == null) {
            mCursor = new Cursor<>(GetAction.this);
        }

        GraphRequest requestNextPage =
                response.getRequestForPagedResults(GraphResponse.PagingDirection.NEXT);
        if (requestNextPage != null) {
            requestNextPage.setCallback(mCallback);
        }
        mCursor.setNextPage(requestNextPage);

        GraphRequest requestPrevPage =
                response.getRequestForPagedResults(GraphResponse.PagingDirection.PREVIOUS);
        if (requestPrevPage != null) {
            requestPrevPage.setCallback(mCallback);
        }
        mCursor.setPrevPage(requestPrevPage);

        // TODO find a solution here
        //mSingleEmitter.setCursor(mCursor);
    }

    public void setBundle(Bundle bundle) {
        this.bundle = bundle;
    }
}