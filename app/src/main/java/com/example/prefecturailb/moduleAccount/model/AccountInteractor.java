package com.example.prefecturailb.moduleAccount.model;

import com.example.prefecturailb.common.pojo.User;

public interface AccountInteractor {

    void signOut();
    void onSubscribeToMaestros();
    void onUnsubscribeToMaestros();
    void getUserInfo();
    void assistance(User user,String hora, String fecha, String grupo, String salon );
}
