package com.example.mvc.common.dependencyinjection;

import android.content.Context;
import android.view.LayoutInflater;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.example.mvc.networking.StackoverflowApi;
import com.example.mvc.questions.FetchQuestionDetailsUseCase;
import com.example.mvc.questions.FetchQuestionListUseCase;
import com.example.mvc.screens.common.controller.BackPressDispatcher;
import com.example.mvc.screens.common.controller.FragmentFrameWrapper;
import com.example.mvc.screens.common.toasthelper.ToastHelper;
import com.example.mvc.screens.common.screensnavigator.ScreensNavigator;
import com.example.mvc.screens.common.ViewMvcFactory;
import com.example.mvc.screens.questiondetails.QuestionDetailsController;
import com.example.mvc.screens.questionslist.QuestionsListController;

public class ControllerCompositionRoot {

    private final CompositionRoot mCompositionRoot;
    private final FragmentActivity activity;

    public ControllerCompositionRoot(CompositionRoot mCompositionRoot, FragmentActivity activity) {
        this.mCompositionRoot = mCompositionRoot;
        this.activity = activity;
    }

    private FragmentActivity getActivity(){
        return activity;
    }

    private LayoutInflater getLayoutInflater(){
        return LayoutInflater.from(getActivity());
    }

    private Context getContext(){
        return getActivity();
    }

    private FragmentManager getFragmentManager(){
        return getActivity().getSupportFragmentManager();
    }

    private FragmentFrameWrapper getFragmentFrameWrapper(){
        return (FragmentFrameWrapper) getActivity();
    }

    private BackPressDispatcher getBackPressDispatcher(){
        return (BackPressDispatcher) getActivity();
    }

    private ScreensNavigator getScreensNavigator(){
        return new ScreensNavigator(
                getFragmentManager(),
                getFragmentFrameWrapper()
        );
    }

    private ToastHelper getToastHelper(){
        return new ToastHelper(getContext());
    }

    public StackoverflowApi getStackoverflowApi() {
        return mCompositionRoot.getStackoverflowApi();
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

    public QuestionsListController getQuestionsListController() {
        return new QuestionsListController(
                getFetchQuestionListUseCase(),
                getScreensNavigator(),
                getToastHelper(),
                getBackPressDispatcher()
        );
    }

    public QuestionDetailsController getQuestionDetailsController() {
        return new QuestionDetailsController(
                getFetchQuestionDetailsUseCase(),
                getToastHelper(),
                getScreensNavigator(),
                getBackPressDispatcher()
        );
    }
}
