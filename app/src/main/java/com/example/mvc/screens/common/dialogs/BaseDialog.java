package com.example.mvc.screens.common.dialogs;

import androidx.fragment.app.DialogFragment;

import com.example.mvc.common.CustomApplication;
import com.example.mvc.common.dependencyinjection.activity.ActivityComponent;
import com.example.mvc.common.dependencyinjection.activity.DaggerActivityComponent;
import com.example.mvc.common.dependencyinjection.app.AppComponent;
import com.example.mvc.common.dependencyinjection.app.DaggerAppComponent;
import com.example.mvc.common.dependencyinjection.presentation.DaggerPresentationComponent;
import com.example.mvc.common.dependencyinjection.presentation.PresentationComponent;
import com.example.mvc.common.dependencyinjection.presentation.PresentationModule;
import com.example.mvc.screens.common.main.MainActivity;

public abstract class BaseDialog extends DialogFragment {
    private PresentationModule mPresentationModule;
    private PresentationComponent presentationComponent;


    private PresentationModule getPresentationModule(){
        if(mPresentationModule == null){
            mPresentationModule = new PresentationModule(this);
        }
        return mPresentationModule;
    }

    private PresentationComponent presentationComponent(){
        if(presentationComponent == null){
            presentationComponent = DaggerPresentationComponent
                    .builder()
                    .activityComponent(((MainActivity) requireActivity()).activityComponent())
                    .presentationModule(getPresentationModule())
                    .build();
        }
        return presentationComponent;
    }

    protected PresentationComponent injector() {
        return presentationComponent();
    }
}
