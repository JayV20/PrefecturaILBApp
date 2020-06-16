package com.example.prefecturailb.moduleAccount;

import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.prefecturailb.R;
import com.example.prefecturailb.common.pojo.User;
import com.example.prefecturailb.moduleAccount.events.AccountEvents;
import com.example.prefecturailb.moduleAccount.model.AccountInteractor;
import com.example.prefecturailb.moduleAccount.model.AccountInteractorClass;
import com.example.prefecturailb.moduleAccount.view.AccountView;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AccountPresenterClass implements AccountPresenter{

    private AccountView mView;
    private AccountInteractor mInteractor;
    private User user;

    public AccountPresenterClass (AccountView mView){
        this.mView = mView;
        this.mInteractor = new AccountInteractorClass();
    }


    @Override
    public void onCreate() {
        EventBus.getDefault().register(this);
        mInteractor.getUserInfo();
    }

    @Override
    public void onPause() {
        if(mView != null){
            mInteractor.onUnsubscribeToMaestros();
        }
    }

    @Override
    public void onResume() {
        if (mView != null){
            mInteractor.onSubscribeToMaestros();
        }
    }

    @Override
    public void onDestroy() {
        mView = null;
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

    }

    @Override
    public void onResult(int requestCode, int resultCode, @Nullable Intent data) {
            IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
            if (result!= null){
                String QR= result.getContents();
                if (QR == null){
                    mView.onMessage(R.string.error_canceled);
                }else {
                    String [] QrContent=QR.split(";");
                    String hora=new SimpleDateFormat("hh:mm:ss",Locale.getDefault()).format(new Date());
                    String fecha=new SimpleDateFormat("dd-MM-yyyy",Locale.getDefault()).format(new Date());
                    if (user!=null) {
                    Log.e("Usuario",user.getType()+"  "+user.getName().toUpperCase());
                    ///Log.e("Hora",hora);
                    //Log.e("Fecha",fecha);
                    //Log.e("QR",QrContent[0]+"  "+QrContent[1]);
                        mInteractor.assistance(user,hora,fecha,QrContent[0],QrContent[1]);
                    }
                }
            }
    }

    @Subscribe
    @Override
    public void onEvent(AccountEvents events) {
        switch (events.getTypeEvent()){
            case AccountEvents.ADD_SUCCEFULL:
                mView.onGetList(events.getMaestro());
                break;
            case AccountEvents.GET_USER_SUCCESFULL:
                user=events.getUser();
                break;
            case AccountEvents.VERIFICATION_SUCCESFULL:
                mView.onMessage(R.string.verif_successfull);
                break;
            case AccountEvents.ASSISTANCE_SUCCESS:
                mView.onMessage(R.string.assis_success);
                break;
            case AccountEvents.GET_USER_NETWORK_ERROR:
            case AccountEvents.CONNECTION_ERROR:
            case AccountEvents.SEARCH_ERROR:
            case AccountEvents.UNKOWN_ERROR:
            case AccountEvents.NO_GROUP_ERROR:
                mView.onMessage(events.getMessage());
                break;
        }
    }

    @Override
    public void signOut() {
        mInteractor.signOut();
        mView.onOpenLogin();
    }

    @Override
    public void openScan() {
        mView.checkPermissionsToApp();
        mView.openScan();
    }

    @Override
    public void getUserInfo() {
        mInteractor.getUserInfo();
    }

}
