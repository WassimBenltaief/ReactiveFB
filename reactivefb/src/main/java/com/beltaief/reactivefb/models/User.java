package com.beltaief.reactivefb.models;

public class User extends IdName {

    public User() {
    }

    public User(String id, String name) {
        mId = id;
        mName = name;
    }

    public User(IdName idName) {
        mId = idName.mId;
        mName = idName.mName;
    }
}
