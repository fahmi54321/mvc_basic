package com.example.mvc.common.dependencyinjection;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;

import com.example.mvc.networking.StackoverflowApi;
import com.example.mvc.questions.FetchQuestionDetailsUseCase;
import com.example.mvc.questions.FetchQuestionListUseCase;
import com.example.mvc.screens.common.MessagesDisplayer;
import com.example.mvc.screens.common.ScreensNavigator;
import com.example.mvc.screens.common.ViewMvcFactory;
import com.example.mvc.screens.questiondetails.QuestionDetailsController;
import com.example.mvc.screens.questionslist.QuestionsListController;

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

    public QuestionsListController getQuestionsListController() {
        return new QuestionsListController(
                getFetchQuestionListUseCase(),
                getScreensNavigator(),
                getMessagesDisplayer()
        );
    }

    private Context getContext(){
        return activity;
    }

    private ScreensNavigator getScreensNavigator(){
        return new ScreensNavigator(getContext());
    }

    private MessagesDisplayer getMessagesDisplayer(){
        return new MessagesDisplayer(getContext());
    }

    public QuestionDetailsController getQuestionDetailsController() {
        return new QuestionDetailsController(
                getFetchQuestionDetailsUseCase(),
                getMessagesDisplayer()
        );
    }
}
