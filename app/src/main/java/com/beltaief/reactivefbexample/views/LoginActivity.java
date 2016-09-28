package com.beltaief.reactivefbexample.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.beltaief.reactivefacebook.actions.login.ReactiveLogin;
import com.beltaief.reactivefacebook.example.R;
import com.facebook.login.LoginResult;

import io.reactivex.MaybeObserver;
import io.reactivex.disposables.Disposable;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = LoginActivity.class.getSimpleName();
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button button = (Button) findViewById(R.id.button);
        result = (TextView) findViewById(R.id.result);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }

    /**
     * call login returns a MaybeObserver<LoginResult>
     * onSuccess : returns a LoginResult
     * onError : returns a FacebookException
     * onComplete : called when a login terminates with no result, like onCanceled.
     */
    private void login() {

        ReactiveLogin.login(this).subscribe(new MaybeObserver<LoginResult>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe");
                result.append("onSubscribe");
                result.append("\n");
            }

            @Override
            public void onSuccess(LoginResult value) {
                Log.d(TAG, "OnSuccess");
                result.append("\n");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError " + e.getMessage());
                result.append("onError");
                result.append("\n");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete");
                result.append("onComplete");
                result.append("\n");
            }
        });
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
