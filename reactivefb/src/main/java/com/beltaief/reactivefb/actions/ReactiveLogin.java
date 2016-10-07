package com.beltaief.reactivefb.actions;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import com.beltaief.reactivefb.ReactiveFB;
import com.beltaief.reactivefb.util.PermissionHelper;
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

    /**
     * Login with com.facebook.login.LoginManager
     * The reason why this method returns a MayBe is that when a user cancel the operation, it
     * does not emit any value neither an error. Maybe have a onComplete method that is covering
     * this case.
     * Use onSuccess to handle a success result.
     * Use onComplete to handle canceled operation.
     *
     * @param activity instance of the current activity
     * @return a Maybe of LoginResult
     */
    @NonNull
    public static Maybe<LoginResult> login(Activity activity) {
        checkNotNull(activity, "view == null");
        // save a weak reference to the activity
        ReactiveFB.getSessionManager().setActivity(activity);
        // login
        return MaybeFromObservable.create(new LoginOnSubscribe());
    }

    /**
     * Login with com.facebook.login.widget.LoginButton
     * To be called from an Activity.
     * The reason why it's returning an Observable and not a MayBe like login() is that with MayBe
     * the subscribtion is done only one time. We need an observable in order to subscribe
     * continuously.
     *
     * @param loginButton instance of a com.facebook.login.widget.LoginButton
     * @return an Observable of LoginResult
     */
    @NonNull
    public static Observable<LoginResult> loginWithButton(@NonNull final LoginButton loginButton) {

        checkNotNull(loginButton, "view == null");
        ReactiveFB.checkInit();
        // login
        return Observable.create(new LoginWithButtonOnSubscribe(loginButton));
    }

    /**
     * Login with com.facebook.login.widget.LoginButton
     * To be called from an android.support.v4.app.Fragment
     *
     * @param loginButton instance of a com.facebook.login.widget.LoginButton
     * @param fragment    instance of support android.support.v4.app.Fragment
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
     * Login with com.facebook.login.widget.LoginButton
     * To be called from an android.app.Fragment
     *
     * @param loginButton instance of com.facebook.login.widget.LoginButton
     * @param fragment    instance of android.app.Fragment
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


    /**
     * Redirect facebook callback onActivityResult to this library callback.
     * This is necessary with login/ loginWithButton methods.
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @NonNull
    public static void onActivityResult(int requestCode, int resultCode, Intent data) {
        ReactiveFB.getSessionManager()
                .getCallbackManager()
                .onActivityResult(requestCode, resultCode, data);
    }

    /**
     * Request additional permissions from facebook. This launches a new login with additional
     * permissions.
     *
     * @param permissions List of {@link PermissionHelper}
     * @param activity    current activity instance
     * @return a Maybe of LoginResult.
     */
    public static Maybe<LoginResult> requestAdditionalPermission(List<PermissionHelper> permissions,
                                                                 Activity activity) {

        checkNotNull(permissions, "permissions == null");
        checkNotNull(activity, "permissions == null");

        ReactiveFB.getSessionManager().setActivity(activity);

        return MaybeFromObservable.create(new AdditionalPermissionOnSubscribe(permissions));
    }
}
