package com.example.mvc.screens.common.controller;


import androidx.fragment.app.Fragment;

import com.example.mvc.common.dependencyinjection.presentation.DaggerPresentationComponent;
import com.example.mvc.common.dependencyinjection.presentation.PresentationComponent;
import com.example.mvc.common.dependencyinjection.presentation.PresentationModule;
import com.example.mvc.screens.common.main.MainActivity;

public class BaseFragment extends Fragment {

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
