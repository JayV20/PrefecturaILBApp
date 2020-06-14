package com.example.prefecturailb.moduleAccount.model;

import com.example.prefecturailb.common.pojo.Maestro;
import com.example.prefecturailb.common.pojo.User;
import com.example.prefecturailb.moduleAccount.events.AccountEvents;
import com.example.prefecturailb.moduleAccount.model.dataAccess.Authentication;
import com.example.prefecturailb.moduleAccount.model.dataAccess.MaestrosEventListener;
import com.example.prefecturailb.moduleAccount.model.dataAccess.RealTimeDataBase;
import com.example.prefecturailb.moduleAccount.model.dataAccess.UserCallBack;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

public class AccountInteractorClass implements AccountInteractor{

    Authentication mAuthentication;
    RealTimeDataBase mRealTimeDataBase;

    public AccountInteractorClass (){
        mAuthentication = new Authentication();
        mRealTimeDataBase = new RealTimeDataBase();
    }


    @Override
    public void signOut() {
        mAuthentication.signOut();
    }

    @Override
    public void onSubscribeToMaestros() {
        mRealTimeDataBase.onSubscribeToMaestros(new MaestrosEventListener() {
            @Override
            public void onDataChange(ArrayList<Maestro> maestros, int typeEvent) {
                post(maestros, typeEvent);
            }

            @Override
            public void onChildError(int resMsg, int typeEvent) {
                post(resMsg, typeEvent);
            }
        });
        }

    @Override
    public void onUnsubscribeToMaestros() {
        mRealTimeDataBase.onUnsubscribeMaestros();
    }

    @Override
    public void getUserInfo(){
        mRealTimeDataBase.getUserByEmail(mAuthentication.getUserEmail(), new UserCallBack() {
            @Override
            public void getUserByEmail(int type, User user) {
                post(type,user);
            }

            @Override
            public void onError(int type, int resMsg) {
                post(type, resMsg);
            }
        });
    }


    private void post(ArrayList<Maestro> maestros, int typeEvent, int resMsg, User user){
        AccountEvents events = new AccountEvents();
        events.setMessage(resMsg);
        events.setTypeEvent(typeEvent);
        events.setMaestro(maestros);
        events.setUser(user);
        EventBus.getDefault().post(events);
    }

    private void post(ArrayList<Maestro> maestros, int typeEvent){
        post(maestros, typeEvent, 0, null);
    }

    private void post(int type,User user){
        post(null,type, 0, user);
    }

    private void post(int typeEvent, int resMsg){
        post(null, typeEvent, resMsg, null);
    }

}
