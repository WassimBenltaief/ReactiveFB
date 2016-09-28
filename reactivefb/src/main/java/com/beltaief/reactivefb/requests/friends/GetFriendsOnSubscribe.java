package com.beltaief.reactivefb.requests.friends;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.beltaief.reactivefb.ReactiveFB;
import com.beltaief.reactivefb.models.Profile;

import java.util.List;

import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;

/**
 * Created by wassim on 9/19/16.
 */
public class GetFriendsOnSubscribe implements SingleOnSubscribe<List<Profile>> {

    String mBundleString;

    public GetFriendsOnSubscribe(@Nullable String bundle) {
        mBundleString = bundle;
    }

    @Override
    public void subscribe(SingleEmitter<List<Profile>> emitter) throws Exception {
        getFriends(emitter);
    }

    private void getFriends(SingleEmitter<List<Profile>> emitter) {
        GetFriendsAction getFriendsAction = new GetFriendsAction(ReactiveFB.getSessionManager());
        getFriendsAction.setBundle(getBundle());
        getFriendsAction.setSingleEmitter(emitter);
        getFriendsAction.execute();
    }

    public Bundle getBundle() {
        Bundle bundle = new Bundle();
        bundle.putString("fields", mBundleString);
        return bundle;
    }
}
