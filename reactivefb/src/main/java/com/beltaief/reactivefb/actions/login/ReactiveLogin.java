package com.beltaief.reactivefb.actions.login;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import com.beltaief.reactivefb.ReactiveFB;
import com.beltaief.reactivefb.models.Permission;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.internal.operators.maybe.MaybeFromObservable;

import static com.beltaief.reactivefb.util.Checker.checkNotNull;


/**
 * A static factory to create an Observable of login events.
 */
public class ReactiveLogin {

    @NonNull
    public static Maybe<LoginResult> login(Activity activity) {
        checkNotNull(activity, "view == null");
        // save a weak reference to the activity
        ReactiveFB.getSessionManager().setActivity(activity);
        // login
        return MaybeFromObservable.create(new LoginOnSubscribe());
    }

    /**
     * To be called from an Activity
     * @param loginButton
     * @return
     */
    @NonNull
    public static Observable<LoginResult> loginWithButton(@NonNull final LoginButton loginButton) {

        checkNotNull(loginButton, "view == null");
        ReactiveFB.checkInit();
        // login
        return Observable.create(new LoginWithButtonOnSubscribe(loginButton));
    }

    /**
     * To be called from an android.support.v4.app.fragment
     * @param loginButton instance of the facebook LoginButton
     * @param fragment support lib fragment
     * @return
     */
    @NonNull
    public static Observable<LoginResult> loginWithButton(@NonNull final LoginButton loginButton,
                                                          @NonNull Fragment fragment) {

        checkNotNull(loginButton, "fragment == null");
        ReactiveFB.checkInit();
        // login
        return Observable.create(new LoginWithButtonOnSubscribe(loginButton));
    }

    /**
     * To be called from an android.app.fragment
     * @param loginButton instance of the facebook LoginButton
     * @param fragment support lib fragment
     * @return
     */
    @NonNull
    public static Observable<LoginResult> loginWithButton(@NonNull final LoginButton loginButton,
                                                          @NonNull android.app.Fragment fragment) {

        checkNotNull(loginButton, "fragment == null");
        ReactiveFB.checkInit();
        // login
        return Observable.create(new LoginWithButtonOnSubscribe(loginButton));
    }


    @NonNull
    public static void onActivityResult(int requestCode, int resultCode, Intent data) {
        ReactiveFB.getSessionManager()
                .getCallbackManager()
                .onActivityResult(requestCode, resultCode, data);
    }

    public static Maybe<LoginResult> requestAdditionalPermission(List<Permission> permissions,
                                                                 Activity activity) {

        checkNotNull(permissions, "permissions == null");
        checkNotNull(activity, "permissions == null");

        ReactiveFB.getSessionManager().setActivity(activity);

        return MaybeFromObservable.create(new AdditionalPermissionOnSubscribe(permissions));
    }
}
