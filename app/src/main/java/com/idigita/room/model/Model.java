package com.idigita.room.model;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.idigita.room.db.MyDatabase;

public class Model {
    private static Model mSingleton;
    private MyDatabase myDatabase;
    private Context context;

    private Model(Context context){
        this.context = context;
        myDatabase = Room.databaseBuilder(context,MyDatabase.class,"TODO").build();
    }
    public static Model getInstance(Context context){
        if(mSingleton == null){
            mSingleton = new Model(context);
        }

        return mSingleton;
    }

    public MyDatabase getMyDatabase(){
        return myDatabase;
    }
}
