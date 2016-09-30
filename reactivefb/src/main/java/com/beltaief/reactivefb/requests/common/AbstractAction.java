package com.beltaief.reactivefb.requests.common;

import com.beltaief.reactivefb.ReactiveFB;
import com.beltaief.reactivefb.SessionManager;
import com.beltaief.reactivefb.SimpleFacebookConfiguration;

abstract class AbstractAction {

    SessionManager sessionManager;
    SimpleFacebookConfiguration configuration = ReactiveFB.getConfiguration();

    AbstractAction(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    public void execute() {
        executeImpl();
    }

    protected abstract void executeImpl();
}
