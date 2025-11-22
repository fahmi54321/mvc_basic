package com.example.mvc.screens.common.controller;


import androidx.appcompat.app.AppCompatActivity;

import com.example.mvc.common.CustomApplication;
import com.example.mvc.common.dependencyinjection.activity.ActivityComponent;
import com.example.mvc.common.dependencyinjection.activity.ActivityModule;
import com.example.mvc.common.dependencyinjection.activity.DaggerActivityComponent;
import com.example.mvc.common.dependencyinjection.app.AppComponent;
import com.example.mvc.common.dependencyinjection.app.DaggerAppComponent;
import com.example.mvc.common.dependencyinjection.presentation.DaggerPresentationComponent;
import com.example.mvc.common.dependencyinjection.presentation.PresentationComponent;
import com.example.mvc.common.dependencyinjection.presentation.PresentationModule;

public class BaseActivity extends AppCompatActivity {

    private PresentationModule mPresentationModule;

    private ActivityModule mActivityModule;
    private PresentationComponent presentationComponent;
    private ActivityComponent activityComponent;
    private AppComponent appComponent;

    private AppComponent appComponent(){
        if(appComponent == null){
            appComponent = DaggerAppComponent
                    .builder()
                    .appModule(((CustomApplication) getApplication()).getAppModule())
                    .build();
        }
        return appComponent;
    }

    public ActivityModule getActivityModule(){
        if(mActivityModule == null){
            mActivityModule = new ActivityModule(
                    this
            );
        }
        return mActivityModule;
    }

    public ActivityComponent activityComponent(){
        if(activityComponent == null){
            activityComponent = DaggerActivityComponent
                    .builder()
                    .appComponent(appComponent())
                    .activityModule(getActivityModule())
                    .build();
        }
        return activityComponent;
    }

    private PresentationModule getPresentationModule(){
        if(mPresentationModule == null){
            mPresentationModule = new PresentationModule();
        }
        return mPresentationModule;
    }

    private PresentationComponent presentationComponent(){
        if(presentationComponent == null){
            presentationComponent = DaggerPresentationComponent
                    .builder()
                    .activityComponent(activityComponent())
                    .presentationModule(getPresentationModule())
                    .build();
        }
        return presentationComponent;
    }

    protected PresentationComponent injector(){
        return presentationComponent();
    }
}
