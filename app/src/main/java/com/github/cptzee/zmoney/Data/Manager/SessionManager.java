package com.github.cptzee.zmoney.Data.Manager;

public class SessionManager {
    private static SessionManager instance;

    public static SessionManager getInstance() {
        if(instance == null)
            instance = new SessionManager();
        return instance;
    }

    private boolean firstInstance = true;

    public boolean isFirstInstance() {
        return firstInstance;
    }

    public void setFirstInstance(boolean firstInstance) {
        this.firstInstance = firstInstance;
    }
}
