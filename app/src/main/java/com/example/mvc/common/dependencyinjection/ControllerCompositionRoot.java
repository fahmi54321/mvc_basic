package com.example.mvc.common.dependencyinjection;

import android.content.Context;
import android.view.LayoutInflater;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.example.mvc.common.permissions.CameraHelper;
import com.example.mvc.common.permissions.ImageHelper;
import com.example.mvc.common.permissions.PermissionsHelper;
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

    private final ActivityCompositionRoot activityCompositionRoot;
    private final Fragment fragment;

    private CameraHelper cameraHelper;
    private PermissionsHelper permissionsHelper;
    private ImageHelper imageHelper;

    public ControllerCompositionRoot(ActivityCompositionRoot activityCompositionRoot) {
        this.activityCompositionRoot = activityCompositionRoot;
        this.fragment = null;
    }

    public ControllerCompositionRoot(ActivityCompositionRoot activityCompositionRoot, Fragment fragment) {
        this.activityCompositionRoot = activityCompositionRoot;
        this.fragment = fragment;
    }

    private FragmentActivity getActivity(){
        return activityCompositionRoot.getActivity();
    }

    private LayoutInflater getLayoutInflater(){
        return LayoutInflater.from(getActivity());
    }

    private Context getContext(){
        return activityCompositionRoot.getContext();
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
        return activityCompositionRoot.getStackoverflowApi();
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

    public PermissionsHelper getPermissionsHelper(){
        if(fragment != null){
            if(permissionsHelper == null) {
                permissionsHelper = new PermissionsHelper(fragment, getContext());
            }
            return permissionsHelper;
        }else{
            if(permissionsHelper == null) {
                permissionsHelper = new PermissionsHelper(getActivity(), getContext());
            }
            return permissionsHelper;
        }
    }

    public CameraHelper getCameraPicker(){
        if(fragment != null){
            if(cameraHelper == null){
                cameraHelper = new CameraHelper(fragment, getContext());
            }
            return cameraHelper;
        }else{
            if(cameraHelper == null){
                cameraHelper = new CameraHelper(getActivity(), getContext());
            }
            return cameraHelper;
        }
    }

    public ImageHelper getImageHelper() {
        if(fragment != null){
            if(imageHelper == null){
                imageHelper = new ImageHelper(fragment, getContext());
            }
            return imageHelper;
        }else{
            if(imageHelper == null){
                imageHelper = new ImageHelper(getActivity(), getContext());
            }
            return imageHelper;
        }
    }



    public QuestionDetailsController getQuestionDetailsController() {
        return new QuestionDetailsController(
                getFetchQuestionDetailsUseCase(),
                getToastHelper(),
                getScreensNavigator(),
                getDialogsManager(),
                getDialogsEventBus(),
                getPermissionsHelper(),
                getCameraPicker(),
                getImageHelper()
        );
    }

    public DialogsEventBus getDialogsEventBus() {
        return activityCompositionRoot.getDialogsEventBus();
    }
}
