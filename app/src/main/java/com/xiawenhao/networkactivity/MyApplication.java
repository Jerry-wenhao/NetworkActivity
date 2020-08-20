package com.xiawenhao.networkactivity;

import android.app.Application;

import androidx.room.Room;

public class MyApplication extends Application {
    private static MyApplication myApplication;
    private static LocalDatabase localDatabase;

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
        localDatabase = Room.databaseBuilder(getApplicationContext(),
                LocalDatabase.class,
                "person").build();
    }

    public static MyApplication myApplication() {
        return myApplication;
    }

    public static LocalDatabase localDatabase() {
        return localDatabase;
    }
}

