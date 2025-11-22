package com.example.mvc.common.dependencyinjection.presentation;

import android.content.Context;
import android.view.LayoutInflater;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.example.mvc.common.permissions.CameraHelper;
import com.example.mvc.common.permissions.ImageHelper;
import com.example.mvc.common.permissions.PermissionsHelper;
import com.example.mvc.networking.StackoverflowApi;
import com.example.mvc.questions.FetchQuestionDetailsUseCase;
import com.example.mvc.questions.FetchQuestionListUseCase;
import com.example.mvc.screens.common.dialogs.DialogsEventBus;
import com.example.mvc.screens.common.dialogs.DialogsManager;
import com.example.mvc.screens.common.navdrawer.NavDrawerHelper;
import com.example.mvc.screens.common.toasthelper.ToastHelper;
import com.example.mvc.screens.common.screensnavigator.ScreensNavigator;
import com.example.mvc.screens.common.ViewMvcFactory;
import com.example.mvc.screens.questiondetails.QuestionDetailsController;
import com.example.mvc.screens.questionslist.QuestionsListController;

import dagger.Module;
import dagger.Provides;

@Module
public class PresentationModule {
    private final Fragment fragment;

    public PresentationModule() {
        this.fragment = null;
    }

    public PresentationModule(Fragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    public PermissionsHelper getPermissionsHelper(Context context, FragmentActivity fragmentActivity){
        if(fragment != null){
            return new PermissionsHelper(fragment, context);
        }else{
            return new PermissionsHelper(fragmentActivity, context);
        }
    }

    @Provides
    public CameraHelper getCameraHelper(Context context, FragmentActivity fragmentActivity){
        if(fragment != null){
            return new CameraHelper(fragment, context);
        }else{
            return new CameraHelper(fragmentActivity, context);
        }
    }

    @Provides
    public ImageHelper getImageHelper(Context context, FragmentActivity fragmentActivity) {
        if(fragment != null){
            return new ImageHelper(fragment, context);
        }else{
            return new ImageHelper(fragmentActivity, context);
        }
    }

    @Provides
    public ViewMvcFactory getViewMvcFactory(LayoutInflater layoutInflater, NavDrawerHelper navDrawerHelper){
        return new ViewMvcFactory(layoutInflater, navDrawerHelper);
    }

    @Provides
    public FetchQuestionDetailsUseCase getFetchQuestionDetailsUseCase(StackoverflowApi stackoverflowApi) {
        return new FetchQuestionDetailsUseCase(stackoverflowApi);
    }

    @Provides
    public FetchQuestionListUseCase getFetchQuestionListUseCase(StackoverflowApi stackoverflowApi) {
        return new FetchQuestionListUseCase(stackoverflowApi);
    }

    @Provides
    public QuestionDetailsController getQuestionDetailsController(
            FetchQuestionDetailsUseCase fetchQuestionDetailsUseCase,
            ToastHelper toastHelper,
            ScreensNavigator screensNavigator,
            DialogsManager dialogsManager,
            DialogsEventBus dialogsEventBus,
            PermissionsHelper permissionsHelper,
            CameraHelper cameraHelper,
            ImageHelper imageHelper
    ) {
        return new QuestionDetailsController(
                fetchQuestionDetailsUseCase,
                toastHelper,
                screensNavigator,
                dialogsManager,
                dialogsEventBus,
                permissionsHelper,
                cameraHelper,
                imageHelper
        );
    }

    @Provides
    public QuestionsListController getQuestionsListController(
            FetchQuestionListUseCase fetchQuestionListUseCase,
            ScreensNavigator screensNavigator,
            ToastHelper toastHelper,
            DialogsManager dialogsManager,
            DialogsEventBus dialogsEventBus
    ) {
        return new QuestionsListController(
                fetchQuestionListUseCase,
                screensNavigator,
                toastHelper,
                dialogsManager,
                dialogsEventBus
        );
    }
}
