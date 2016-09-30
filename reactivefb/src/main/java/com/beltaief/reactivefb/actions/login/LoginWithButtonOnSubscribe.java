package com.beltaief.reactivefb.actions.login;

import android.app.Fragment;
import com.beltaief.reactivefb.ReactiveFB;
import com.beltaief.reactivefb.SessionManager;
import com.beltaief.reactivefb.SimpleFacebookConfiguration;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import java.lang.ref.WeakReference;
import java.util.List;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 * Created by wassim on 9/16/16.
 */
final class LoginWithButtonOnSubscribe implements ObservableOnSubscribe<LoginResult> {

    private final LoginButton mLoginButton;
    private final SessionManager sessionManager;
    private SimpleFacebookConfiguration simpleFacebookConfiguration;
    private WeakReference<Fragment> mFragment;

    LoginWithButtonOnSubscribe(LoginButton loginButton) {
        mLoginButton = loginButton;
        sessionManager = ReactiveFB.getSessionManager();
        simpleFacebookConfiguration = ReactiveFB.getConfiguration();
    }

    public LoginWithButtonOnSubscribe(LoginButton loginButton, Fragment fragment) {
        mLoginButton = loginButton;
        sessionManager = ReactiveFB.getSessionManager();
        simpleFacebookConfiguration = ReactiveFB.getConfiguration();
        mFragment = new WeakReference<>(fragment);
    }

    @Override
    public void subscribe(ObservableEmitter<LoginResult> emitter) throws Exception {
        if (sessionManager.isLoggedIn()) {
            LoginResult loginResult = sessionManager.createLastLoginResult();
            emitter.onNext(loginResult);
            emitter.onComplete();
            return;
        }
        List<String> mPermissions = simpleFacebookConfiguration.getReadPermissions();
        if (mPermissions != null) {
            mLoginButton.setReadPermissions(mPermissions);
        }

        // Sets the fragment that contains this control.
        // This allows the button to be embedded inside a Fragment, and will allow the fragment
        // to receive the {@link Fragment#onActivityResult(int, int, android.content.Intent)
        // onActivityResult} call rather than the Activity.
        if (mFragment != null) {
            mLoginButton.setFragment(mFragment.get());
        }

        // set observableEmitter
        sessionManager.setCallbackAsLoginWithButton(emitter);
    }
}
