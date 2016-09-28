package com.beltaief.reactivefb.requests.profile;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.beltaief.reactivefb.ReactiveFB;
import com.beltaief.reactivefb.models.Profile;

import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;

/**
 * Created by wassim on 9/16/16.
 */
public class GetProfileOnSubscribe implements SingleOnSubscribe<Profile> {

    String mBundleString;
    String mProfileId;

    public GetProfileOnSubscribe(@Nullable String bundle, @Nullable String profileId) {
        mBundleString = bundle;
        mProfileId = profileId;
    }

    @Override
    public void subscribe(SingleEmitter<Profile> emitter) throws Exception {
        getProfile(emitter);
    }

    private void getProfile(SingleEmitter<Profile> emitter) {
        GetProfileAction getProfileAction = new GetProfileAction(ReactiveFB.getSessionManager());
        getProfileAction.setBundle(getBundle());
        getProfileAction.setTarget(mProfileId);
        getProfileAction.setSingleEmitter(emitter);
        getProfileAction.execute();
    }

    public Bundle getBundle() {
        Bundle bundle = new Bundle();
        bundle.putString("fields", mBundleString);
        return bundle;
    }
}
