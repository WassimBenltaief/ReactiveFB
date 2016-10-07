package com.beltaief.reactivefb.actions;

import com.beltaief.reactivefb.ReactiveFB;
import com.beltaief.reactivefb.SessionManager;
import com.beltaief.reactivefb.SimpleFacebookConfiguration;
import com.facebook.login.LoginResult;

import java.util.List;

import io.reactivex.MaybeEmitter;
import io.reactivex.MaybeOnSubscribe;

/**
 * Created by wassim on 9/16/16.
 */
final class LoginOnSubscribe implements MaybeOnSubscribe<LoginResult> {

    private SessionManager sessionManager;
    private SimpleFacebookConfiguration simpleFacebookConfiguration;

    LoginOnSubscribe() {
        sessionManager = ReactiveFB.getSessionManager();
        simpleFacebookConfiguration = ReactiveFB.getConfiguration();
    }

    @Override
    public void subscribe(MaybeEmitter<LoginResult> emitter) throws Exception {
        if (sessionManager.isLoggedIn()) {
            LoginResult loginResult = sessionManager.createLastLoginResult();
            emitter.onSuccess(loginResult);
            return;
        }
        // set observableEmitter
        sessionManager.setCallbackAsLogin(emitter);


        // login please, with all read permissions
        List<String> permissions = simpleFacebookConfiguration.getReadPermissions();
        sessionManager.requestReadPermissions(permissions);
    }

}
