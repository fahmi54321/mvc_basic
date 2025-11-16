package com.example.mvc.screens.common.controller;


import androidx.fragment.app.Fragment;

import com.example.mvc.common.dependencyinjection.ControllerCompositionRoot;
import com.example.mvc.screens.common.main.MainActivity;

public class BaseFragment extends Fragment {

    private ControllerCompositionRoot mControllerCompositionRoot;


    protected ControllerCompositionRoot getCompositionRoot(){
        if(mControllerCompositionRoot == null){
            mControllerCompositionRoot = new ControllerCompositionRoot(
                    ((MainActivity) requireActivity()).getActivityCompositionRoot(),
                    this
            );
        }
        return mControllerCompositionRoot;
    }
}
