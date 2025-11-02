package com.example.mvc.common.dependencyinjection;

import android.app.Activity;
import android.view.LayoutInflater;

import com.example.mvc.networking.StackoverflowApi;

public class ControllerCompositionRoot {

    private final CompositionRoot mCompositionRoot;
    private final Activity activity;

    public ControllerCompositionRoot(CompositionRoot mCompositionRoot, Activity activity) {
        this.mCompositionRoot = mCompositionRoot;
        this.activity = activity;
    }

    public StackoverflowApi getStackoverflowApi() {
        return mCompositionRoot.getStackoverflowApi();
    }

    private LayoutInflater getLayoutInflater(){
        return LayoutInflater.from(activity);
    }
}
