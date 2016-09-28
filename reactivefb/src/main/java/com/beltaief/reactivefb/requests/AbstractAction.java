package com.beltaief.reactivefb.requests;

import com.beltaief.reactivefb.ReactiveFB;
import com.beltaief.reactivefb.SessionManager;
import com.beltaief.reactivefb.SimpleFacebookConfiguration;

public abstract class AbstractAction {

    protected SessionManager sessionManager;
    protected SimpleFacebookConfiguration configuration = ReactiveFB.getConfiguration();

    public AbstractAction(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    public void execute() {
        executeImpl();
    }

    protected abstract void executeImpl();
}
