package com.example.mvc.common.dependencyinjection.activity;

import android.content.Context;
import android.view.LayoutInflater;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.example.mvc.screens.common.dialogs.DialogsManager;
import com.example.mvc.screens.common.fragmentframehelper.FragmentFrameHelper;
import com.example.mvc.screens.common.fragmentframehelper.FragmentFrameWrapper;
import com.example.mvc.screens.common.navdrawer.NavDrawerHelper;
import com.example.mvc.screens.common.screensnavigator.ScreensNavigator;
import com.example.mvc.screens.common.toasthelper.ToastHelper;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {
    private final FragmentActivity activity;

    public ActivityModule(FragmentActivity activity) {
        this.activity = activity;
    }

    @Provides
    public FragmentActivity getActivity(){
        return activity;
    }

    @Provides
    public Context getContext() {
        return activity;
    }

    @Provides
    public LayoutInflater getLayoutInflater(Context context) {
        return LayoutInflater.from(context);
    }

    @Provides
    public FragmentManager getSupportFragmentManager(FragmentActivity fragmentActivity){
        return fragmentActivity.getSupportFragmentManager();
    }

    @Provides
    public FragmentFrameWrapper getFragmentFrameWrapper(FragmentActivity fragmentActivity){
        return (FragmentFrameWrapper) fragmentActivity;
    }

    @Provides
    public NavDrawerHelper getNavDrawerHelper(FragmentActivity fragmentActivity){
        return (NavDrawerHelper) fragmentActivity;
    }

    @ActivityScope
    @Provides
    public ToastHelper getToastHelper(Context context){
        return new ToastHelper(context);
    }

    @ActivityScope
    @Provides
    public FragmentFrameHelper getFragmentFrameHelper(
            FragmentActivity fragmentActivity,
            FragmentFrameWrapper fragmentFrameWrapper,
            FragmentManager fragmentManager
    ){
        return new FragmentFrameHelper(
                fragmentActivity,
                fragmentFrameWrapper,
                fragmentManager
        );
    }

    @ActivityScope
    @Provides
    public ScreensNavigator getScreensNavigator(
            FragmentFrameHelper fragmentFrameHelper
    ){
        return new ScreensNavigator(
                fragmentFrameHelper
        );
    }

    @ActivityScope
    @Provides
    public DialogsManager getDialogsManager(
            FragmentManager fragmentManager
    ){
        return new DialogsManager(fragmentManager);
    }
}
