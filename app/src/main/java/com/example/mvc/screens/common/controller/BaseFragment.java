package com.example.mvc.screens.common.controller;


import androidx.fragment.app.Fragment;

import com.example.mvc.common.dependencyinjection.Injector;
import com.example.mvc.common.dependencyinjection.PresentationCompositionRoot;
import com.example.mvc.screens.common.main.MainActivity;

public class BaseFragment extends Fragment {

    private PresentationCompositionRoot mPresentationCompositionRoot;


    private PresentationCompositionRoot getCompositionRoot() {
        if (mPresentationCompositionRoot == null) {
            mPresentationCompositionRoot = new PresentationCompositionRoot(
                    ((MainActivity) requireActivity()).getActivityCompositionRoot(),
                    this
            );
        }
        return mPresentationCompositionRoot;
    }

    protected Injector injector() {
        return new Injector(
                getCompositionRoot()
        );
    }
}
