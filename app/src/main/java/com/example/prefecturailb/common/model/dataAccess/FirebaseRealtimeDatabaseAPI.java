package com.example.prefecturailb.common.model.dataAccess;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseRealtimeDatabaseAPI {
    public static final String PATH_USUARIOS="usuarios";
    public static final String PATH_MAESTROS = "maestros";

    private DatabaseReference mDatabaseReference;
    private static class SingletonHolder{
        private static final FirebaseRealtimeDatabaseAPI INSTANCE = new FirebaseRealtimeDatabaseAPI();
    }

    public static FirebaseRealtimeDatabaseAPI getInstance(){
        return SingletonHolder.INSTANCE;
    }
    private FirebaseRealtimeDatabaseAPI (){
        this.mDatabaseReference= FirebaseDatabase.getInstance().getReference();
    }

    /*
    * References
    * */

    public DatabaseReference getMaestroReference(){
        return getRootReference().child(PATH_MAESTROS);
    }

    public DatabaseReference getRootReference(){
        return mDatabaseReference.getRoot();
    }

    public DatabaseReference getUsuariosReference(){
        return  getRootReference().child(PATH_USUARIOS);
    }

    public DatabaseReference getMaestroReferenceByName(String Name){
        return getMaestroReference().child(Name);
    }
}
