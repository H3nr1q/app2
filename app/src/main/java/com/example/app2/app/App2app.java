package com.example.app2.app;

import androidx.multidex.MultiDexApplication;

public class App2app extends MultiDexApplication {

    private static App2app sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }

    public static App2app getInstance(){
        return sInstance;
    }

}
