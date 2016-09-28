package com.beltaief.reactivefb;

import android.app.Activity;

import com.beltaief.reactivefb.actions.login.ReactiveLoginCallback;
import com.beltaief.reactivefb.actions.login.ReactiveLoginWithButtonCallback;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import java.lang.ref.WeakReference;
import java.util.List;

import io.reactivex.MaybeEmitter;
import io.reactivex.ObservableEmitter;

/**
 * Created by wassim on 9/15/16.
 */
public class SessionManager {

    private WeakReference<Activity> mActivity;
    static SimpleFacebookConfiguration configuration;
    private final LoginManager mLoginManager;
    private final ReactiveLoginCallback<LoginResult> mLoginCallback = new ReactiveLoginCallback<>();
    private final ReactiveLoginWithButtonCallback<LoginResult> mLoginWithButtonCallback = new ReactiveLoginWithButtonCallback<>();
    private final CallbackManager mCallbackManager = CallbackManager.Factory.create();

    public SessionManager(SimpleFacebookConfiguration simpleFacebookConfiguration) {
        SessionManager.configuration = simpleFacebookConfiguration;
        mLoginManager = LoginManager.getInstance();
        mLoginManager.setDefaultAudience(configuration.getDefaultAudience());
        mLoginManager.setLoginBehavior(configuration.getLoginBehavior());
    }

    public void setActivity(Activity activity) {
        mActivity = new WeakReference<>(activity);
    }

    public Activity getActivity() {
        return mActivity.get();
    }

    public void requestReadPermissions(List<String> permissions) {
        mLoginManager.logInWithReadPermissions(mActivity.get(), permissions);
    }

    public void requestPublishPermissions(List<String> permissions) {
        mLoginManager.logInWithPublishPermissions(mActivity.get(), permissions);
    }

    public LoginResult createLastLoginResult() {
        return new LoginResult(getAccessToken(), getAccessToken().getPermissions(), getAccessToken().getDeclinedPermissions());
    }

    public ReactiveLoginCallback<LoginResult> getLoginCallback() {
        return mLoginCallback;
    }

    /**
     * Check if user is loggedIn
     * @return true id accessToken is not expired, false otherwise.
     */
    public boolean isLoggedIn() {
        AccessToken accessToken = getAccessToken();
        if (accessToken == null) {
            return false;
        }
        return !accessToken.isExpired();
    }

    /**
     * Get the Access Token
     * @return
     */
    public AccessToken getAccessToken() {
        return AccessToken.getCurrentAccessToken();
    }


    public CallbackManager getCallbackManager() {
        return mCallbackManager;
    }

    public SimpleFacebookConfiguration getConfiguration() {
        return configuration;
    }

    /**
     * Set LoginManager to use the callback for LoginWithButton
     */
    public void setCallbackAsLoginWithButton(ObservableEmitter<LoginResult> emitter) {
        mLoginWithButtonCallback.setEmitter(emitter);
        mLoginManager.registerCallback(mCallbackManager, mLoginWithButtonCallback);
    }

    /**
     * Set LoginManager to use the callback for login
     */
    public void setCallbackAsLogin(MaybeEmitter<LoginResult> emitter) {
        mLoginCallback.setEmitter(emitter);
        mLoginManager.registerCallback(mCallbackManager, mLoginCallback);
    }
}
