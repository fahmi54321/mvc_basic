package com.example.mvc.screens.common.controller;


import androidx.appcompat.app.AppCompatActivity;

import com.example.mvc.common.CustomApplication;
import com.example.mvc.common.dependencyinjection.ActivityCompositionRoot;
import com.example.mvc.common.dependencyinjection.ControllerCompositionRoot;

public class BaseActivity extends AppCompatActivity {

    private ControllerCompositionRoot mControllerCompositionRoot;

    private ActivityCompositionRoot mActivityCompositionRoot;

    public ActivityCompositionRoot getActivityCompositionRoot(){
        if(mActivityCompositionRoot == null){
            mActivityCompositionRoot = new ActivityCompositionRoot(
                    this,
                    ((CustomApplication) getApplication()).getCompositionRoot()
            );
        }
        return mActivityCompositionRoot;
    }

    protected ControllerCompositionRoot getCompositionRoot(){
        if(mControllerCompositionRoot == null){
            mControllerCompositionRoot = new ControllerCompositionRoot(
                    getActivityCompositionRoot()
            );
        }
        return mControllerCompositionRoot;
    }
}
