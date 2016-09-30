package com.beltaief.reactivefb.requests.common;

import com.facebook.GraphRequest;

class Cursor<T> {

    private final GetAction<T> mGetAction;
    private GraphRequest mNextPage = null;
    private GraphRequest mPrevPage = null;
    private int mPageNum = 0;

    Cursor(GetAction<T> getAction) {
        mGetAction = getAction;
    }

    public boolean hasNext() {
        return mNextPage != null ? true : false;
    }

    public boolean hasPrev() {
        return mPrevPage != null ? true : false;
    }

    public int getPageNum() {
        return mPageNum;
    }

    public void next() {
        mPageNum++;
        mGetAction.runRequest(mNextPage);
    }

    public void prev() {
        mPageNum--;
        mGetAction.runRequest(mPrevPage);
    }

    void setNextPage(GraphRequest requestNextPage) {
        mNextPage = requestNextPage;
    }

    void setPrevPage(GraphRequest requestPrevPage) {
        mPrevPage = requestPrevPage;
    }
}
