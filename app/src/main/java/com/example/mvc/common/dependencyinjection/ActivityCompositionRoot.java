package com.example.mvc.common.dependencyinjection;

import android.content.Context;

import androidx.fragment.app.FragmentActivity;

import com.example.mvc.networking.StackoverflowApi;
import com.example.mvc.screens.common.dialogs.DialogsEventBus;

public class ActivityCompositionRoot {
    private final FragmentActivity activity;
    private final CompositionRoot compositionRoot;

    public ActivityCompositionRoot(FragmentActivity activity, CompositionRoot compositionRoot) {
        this.activity = activity;
        this.compositionRoot = compositionRoot;
    }

    public FragmentActivity getActivity(){
        return activity;
    }

    public StackoverflowApi getStackoverflowApi() {
        return compositionRoot.getStackoverflowApi();
    }

    public DialogsEventBus getDialogsEventBus() {
        return compositionRoot.getDialogsEventBus();
    }

    public Context getContext() {
        return activity;
    }
}
