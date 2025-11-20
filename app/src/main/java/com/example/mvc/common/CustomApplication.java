package com.example.mvc.common;

import android.app.Application;

import com.example.mvc.common.dependencyinjection.AppCompositionRoot;

public class CustomApplication extends Application {

    private AppCompositionRoot mAppCompositionRoot;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppCompositionRoot = new AppCompositionRoot(this);
    }

    public AppCompositionRoot getCompositionRoot() {
        return mAppCompositionRoot;
    }
}
