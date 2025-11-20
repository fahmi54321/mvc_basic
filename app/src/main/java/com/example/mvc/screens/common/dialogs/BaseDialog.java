package com.example.mvc.screens.common.dialogs;

import androidx.fragment.app.DialogFragment;

import com.example.mvc.common.dependencyinjection.PresentationCompositionRoot;
import com.example.mvc.screens.common.main.MainActivity;

public abstract class BaseDialog extends DialogFragment {
    private PresentationCompositionRoot mPresentationCompositionRoot;

    protected PresentationCompositionRoot getCompositionRoot(){
        if(mPresentationCompositionRoot == null){
            mPresentationCompositionRoot = new PresentationCompositionRoot(
                    ((MainActivity) requireActivity()).getActivityCompositionRoot()
            );
        }
        return mPresentationCompositionRoot;
    }
}
