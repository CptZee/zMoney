package com.github.cptzee.zmoney.Data.Helper;

import android.content.Context;

public class Database {
    private Context context;

    public Database(Context context) {
        this.context = context;
        init();
    }
    private void init(){
        CredentialHelper credentialHelper = CredentialHelper.getInstance(context);

        credentialHelper.onCreate(credentialHelper.getWritableDatabase());
    }
}