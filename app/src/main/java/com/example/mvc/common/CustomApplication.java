package com.example.mvc.common;

import android.app.Application;

import com.example.mvc.common.dependencyinjection.app.AppModule;

public class CustomApplication extends Application {

    private AppModule mAppModule;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppModule = new AppModule(this);
    }

    public AppModule getAppModule() {
        return mAppModule;
    }
}
