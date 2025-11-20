package com.example.mvc.common.dependencyinjection;

import android.app.Application;
import android.content.Context;

import androidx.fragment.app.FragmentActivity;

import com.example.mvc.networking.StackoverflowApi;
import com.example.mvc.screens.common.dialogs.DialogsEventBus;

public class ActivityCompositionRoot {
    private final FragmentActivity activity;
    private final AppCompositionRoot appCompositionRoot;

    public ActivityCompositionRoot(FragmentActivity activity, AppCompositionRoot appCompositionRoot) {
        this.activity = activity;
        this.appCompositionRoot = appCompositionRoot;
    }

    public FragmentActivity getActivity(){
        return activity;
    }

    public StackoverflowApi getStackoverflowApi() {
        return appCompositionRoot.getStackoverflowApi();
    }

    public DialogsEventBus getDialogsEventBus() {
        return appCompositionRoot.getDialogsEventBus();
    }

    public Context getContext() {
        return activity;
    }

    public Application getApplication(){
        return appCompositionRoot.getApplication();
    }
}
