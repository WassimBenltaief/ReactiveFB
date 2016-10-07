package com.beltaief.reactivefbexample.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.beltaief.reactivefb.actions.ReactiveLogin;
import com.beltaief.reactivefbexample.R;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;

public class LoginButtonActivity extends AppCompatActivity {

    private static final String TAG = LoginButtonActivity.class.getSimpleName();
    private LoginButton loginButton;
    private TextView result;
    private final CompositeDisposable disposables = new CompositeDisposable();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_button);

        loginButton = (LoginButton) findViewById(R.id.fb_button_login);
        result = (TextView) findViewById(R.id.result);

        registerlogin();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposables.clear(); // do not send event after activity has been destroyed
    }

    private void registerlogin() {
        DisposableObserver<LoginResult> disposableObserver = ReactiveLogin.loginWithButton(loginButton)
                .subscribeWith(new DisposableObserver<LoginResult>() {
                    @Override
                    public void onNext(LoginResult value) {
                        Log.d(TAG, "onNext");
                        result.append("\n");
                        result.append("token = " + value.getAccessToken().getToken());
                        result.append("\n");
                        result.append("granted permissions = " + value.getRecentlyGrantedPermissions().size());
                        result.append("\n");
                        result.append("denied permissions = " + value.getRecentlyDeniedPermissions().size());
                        result.append("\n");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete");
                        result.append("onComplete");
                        result.append("\n");
                    }
                });
        disposables.add(disposableObserver);
    }

    /**
     * We need to call ReactiveFB.onActivityResult in order to execute the onActivityResult
     * and the login listener callbacks
     * Example : ReactiveFB.onActivityResult(requestCode, resultCode, data);
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ReactiveLogin.onActivityResult(requestCode, resultCode, data);
    }
}
