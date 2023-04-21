package com.github.cptzee.zmoney.Data.Manager;

import androidx.annotation.NonNull;

import com.github.cptzee.zmoney.Data.Credential;

import java.util.ArrayList;
import java.util.List;

public class CredentialManager {
    private static CredentialManager instance;

    public static CredentialManager getInstance() {
        if(instance == null)
            instance = new CredentialManager();
        return instance;
    }

    private final List<Credential> list = new ArrayList();

    public void add(@NonNull Credential data){
        data.setID(list.size() + 1);
        list.add(data);
    }

    public List<Credential> getList(){
        return list;
    }

    public Credential get(int ID){
        return list.get(ID - 1);
    }

    public void update(Credential credential){
        Credential toUpdate = list.get(credential.getID() - 1);
        toUpdate.setEncryptedPassword(credential.getEncryptedPassword());
        toUpdate.setPin(credential.getPin());
    }
}
