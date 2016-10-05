package com.beltaief.reactivefb.models;

import android.os.Bundle;

import com.beltaief.reactivefb.util.GraphPath;

public class Like extends User implements Publishable {

    public User getUser() {
        return this;
    }

    @Override
    public Bundle getBundle() {
        return new Bundle();
    }

    @Override
    public String getPath() {
        return GraphPath.LIKES;
    }

    @Override
    public Permission getPermission() {
        return Permission.PUBLISH_ACTION;
    }

    /**
     * Builder for preparing the Like object to be published.
     */
    public static class Builder {

        public Builder() {
        }

        public Like build() {
            return new Like();
        }
    }
}
