package com.example.mvc.common.dependencyinjection;

import android.app.Activity;
import android.view.LayoutInflater;

import com.example.mvc.networking.StackoverflowApi;
import com.example.mvc.questions.FetchQuestionDetailsUseCase;
import com.example.mvc.questions.FetchQuestionListUseCase;
import com.example.mvc.screens.common.ViewMvcFactory;

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

    public ViewMvcFactory getViewMvcFactory(){
        return new ViewMvcFactory(getLayoutInflater());
    }

    public FetchQuestionDetailsUseCase getFetchQuestionDetailsUseCase() {
        return new FetchQuestionDetailsUseCase(getStackoverflowApi());
    }

    public FetchQuestionListUseCase getFetchQuestionListUseCase() {
        return new FetchQuestionListUseCase(getStackoverflowApi());
    }
}
