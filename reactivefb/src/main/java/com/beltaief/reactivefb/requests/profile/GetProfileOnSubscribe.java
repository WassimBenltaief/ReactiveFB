package com.beltaief.reactivefb.requests.profile;

import android.support.annotation.Nullable;

import com.beltaief.reactivefb.ReactiveFB;
import com.beltaief.reactivefb.models.Profile;
import com.beltaief.reactivefb.util.Utils;

import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;

/**
 * Created by wassim on 9/16/16.
 */
public class GetProfileOnSubscribe implements SingleOnSubscribe<Profile> {

    private String mBundleString;
    private String mProfileId;

    public GetProfileOnSubscribe(@Nullable String bundle, @Nullable String profileId) {
        mBundleString = bundle;
        mProfileId = profileId;
    }

    @Override
    public void subscribe(SingleEmitter<Profile> emitter) throws Exception {
        GetProfileAction getProfileAction = new GetProfileAction(ReactiveFB.getSessionManager());
        getProfileAction.setBundle(Utils.getBundle(mBundleString));
        getProfileAction.setTarget(mProfileId);
        getProfileAction.setSingleEmitter(emitter);
        getProfileAction.execute();
    }
}
