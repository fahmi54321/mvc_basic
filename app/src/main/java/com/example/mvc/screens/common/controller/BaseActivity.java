package com.example.mvc.screens.common.controller;


import androidx.appcompat.app.AppCompatActivity;

import com.example.mvc.common.CustomApplication;
import com.example.mvc.common.dependencyinjection.ActivityCompositionRoot;
import com.example.mvc.common.dependencyinjection.Injector;
import com.example.mvc.common.dependencyinjection.PresentationCompositionRoot;

public class BaseActivity extends AppCompatActivity {

    private PresentationCompositionRoot mPresentationCompositionRoot;

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

    protected PresentationCompositionRoot getCompositionRoot(){
        if(mPresentationCompositionRoot == null){
            mPresentationCompositionRoot = new PresentationCompositionRoot(
                    getActivityCompositionRoot()
            );
        }
        return mPresentationCompositionRoot;
    }

    protected Injector injector(){
        return new Injector(getCompositionRoot());
    }
}
