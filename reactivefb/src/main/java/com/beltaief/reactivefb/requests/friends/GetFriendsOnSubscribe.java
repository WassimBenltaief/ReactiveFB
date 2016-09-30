package com.beltaief.reactivefb.requests.friends;

import android.support.annotation.Nullable;

import com.beltaief.reactivefb.ReactiveFB;
import com.beltaief.reactivefb.models.Profile;
import com.beltaief.reactivefb.util.GraphPath;
import com.beltaief.reactivefb.util.Utils;

import java.util.List;

import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;

/**
 * Created by wassim on 9/19/16.
 */
public class GetFriendsOnSubscribe implements SingleOnSubscribe<List<Profile>> {

    private String mBundleString;

    public GetFriendsOnSubscribe(@Nullable String bundle) {
        mBundleString = bundle;
    }

    @Override
    public void subscribe(SingleEmitter<List<Profile>> emitter) throws Exception {
        String target = String.format("%s/%s", "me", GraphPath.FRIENDS);

        GetFriendsAction getAction = new GetFriendsAction(ReactiveFB.getSessionManager());
        getAction.setBundle(Utils.getBundle(mBundleString));
        getAction.setTarget(target);
        getAction.setSingleEmitter(emitter);
        getAction.execute();
    }
}
