package com.example.prefecturailb.moduleAccount.model.dataAccess;

public interface GroupExistCallback {
    void onSuccess();
    void onNotExist();
    void onError();
}
