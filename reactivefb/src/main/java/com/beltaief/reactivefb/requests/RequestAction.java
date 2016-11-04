package com.beltaief.reactivefb.requests;

import android.os.Bundle;

import com.beltaief.reactivefb.ReactiveFB;
import com.beltaief.reactivefb.SessionManager;
import com.beltaief.reactivefb.SimpleFacebookConfiguration;
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

import io.reactivex.SingleEmitter;

/**
 * Execute a GraphRequest action and redirect result to rx SingleEmitter
 * Created by wassim on 10/7/16.
 */
class RequestAction {

    private String mTarget = "me"; // default
    private String mEdge = null;
    SingleEmitter<GraphResponse> mSingleEmitter = null;
    private Bundle mBundle;
    private SessionManager sessionManager;
    private SimpleFacebookConfiguration configuration;

    RequestAction() {
        sessionManager = ReactiveFB.getSessionManager();
        configuration = ReactiveFB.getConfiguration();
    }

    private GraphRequest.Callback mCallback = new GraphRequest.Callback() {
        @Override
        public void onCompleted(GraphResponse response) {
            FacebookRequestError error = response.getError();
            if (error != null) {
                Logger.logError(RequestAction.class,
                        "Failed to get what you have requested", error.getException());

                if (mSingleEmitter != null) {
                    mSingleEmitter.onError(error.getException());
                }
            } else {
                if (response.getRawResponse() == null) {
                    Logger.logError(RequestAction.class, "The response GraphObject " +
                            "has null value. Response=" + response.toString(), null);
                    mSingleEmitter.onError(new FacebookGraphResponseException(response,
                            "The response has null value"));
                } else {
                    if (mSingleEmitter != null) {
                        mSingleEmitter.onSuccess(response);
                    }
                }
            }
        }
    };

    public void setTarget(String target) {
        mTarget = target;
    }

    public void setSingleEmitter(SingleEmitter<GraphResponse> singleEmitter) {
        mSingleEmitter = singleEmitter;
    }

    protected void execute() {
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

    private void runRequest(GraphRequest request) {
        request.setCallback(mCallback);
        GraphRequestAsyncTask task = new GraphRequestAsyncTask(request);
        task.execute();
    }

    private String getGraphPath() {
        if (mEdge != null) {
            return mTarget + "/" + mEdge;
        }
        return mTarget;
    }

    /**
     * Update the params to contain 'appsecret_proof' if it was asked in
     * SimpleFacebookConfiguration.
     *
     * @return Updated bundle
     * // @see https://developers.facebook.com/docs/graph-api/securing-requests
     */
    private Bundle updateAppSecretProof() {
        if (configuration.useAppsecretProof()) {
            if (mBundle == null) {
                mBundle = new Bundle();
            }
            mBundle.putString("appsecret_proof", Utils.encode(configuration.getAppSecret(),
                    sessionManager.getAccessToken().getToken()));
        }
        return mBundle;
    }

    public void setBundle(Bundle bundle) {
        mBundle = bundle;
    }

    public void setEdge(String edge) {
        if(edge != null) {
            mEdge = edge;
        }
    }
}
