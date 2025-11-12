package com.example.mvc.common.dependencyinjection;

import android.content.Context;
import android.view.LayoutInflater;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.example.mvc.networking.StackoverflowApi;
import com.example.mvc.questions.FetchQuestionDetailsUseCase;
import com.example.mvc.questions.FetchQuestionListUseCase;
import com.example.mvc.screens.common.dialogs.DialogsEventBus;
import com.example.mvc.screens.common.dialogs.DialogsManager;
import com.example.mvc.screens.common.fragmentframehelper.FragmentFrameHelper;
import com.example.mvc.screens.common.fragmentframehelper.FragmentFrameWrapper;
import com.example.mvc.screens.common.navdrawer.NavDrawerHelper;
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

    public ScreensNavigator getScreensNavigator(){
        return new ScreensNavigator(
                getFragmentFrameHelper()
        );
    }

    private FragmentFrameHelper getFragmentFrameHelper(){
        return new FragmentFrameHelper(
                getActivity(),
                getFragmentFrameWrapper(),
                getFragmentManager()
        );
    }

    private ToastHelper getToastHelper(){
        return new ToastHelper(getContext());
    }

    private DialogsManager getDialogsManager(){
        return new DialogsManager(getFragmentManager());
    }

    public StackoverflowApi getStackoverflowApi() {
        return mCompositionRoot.getStackoverflowApi();
    }

    public ViewMvcFactory getViewMvcFactory(){
        return new ViewMvcFactory(getLayoutInflater(), getNavDrawerHelper());
    }

    private NavDrawerHelper getNavDrawerHelper(){
        return (NavDrawerHelper) getActivity();
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
                getDialogsManager(),
                getDialogsEventBus()
        );
    }

    public QuestionDetailsController getQuestionDetailsController() {
        return new QuestionDetailsController(
                getFetchQuestionDetailsUseCase(),
                getToastHelper(),
                getScreensNavigator(),
                getDialogsManager(),
                getDialogsEventBus()
        );
    }

    public DialogsEventBus getDialogsEventBus() {
        return mCompositionRoot.getDialogsEventBus();
    }
}
