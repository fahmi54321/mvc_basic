package com.example.mvc.common.dependencyinjection.activity;

import android.content.Context;
import android.view.LayoutInflater;

import androidx.fragment.app.FragmentActivity;

import com.example.mvc.common.dependencyinjection.app.AppComponent;
import com.example.mvc.networking.StackoverflowApi;
import com.example.mvc.screens.common.dialogs.DialogsEventBus;
import com.example.mvc.screens.common.dialogs.DialogsManager;
import com.example.mvc.screens.common.navdrawer.NavDrawerHelper;
import com.example.mvc.screens.common.screensnavigator.ScreensNavigator;
import com.example.mvc.screens.common.toasthelper.ToastHelper;

import dagger.Component;

@ActivityScope
@Component(dependencies = {AppComponent.class},modules = ActivityModule.class)
public interface ActivityComponent {

    Context getContext();
    FragmentActivity getActivity();
    LayoutInflater getLayoutInflater();

    ToastHelper getToastHelper();

    NavDrawerHelper getNavDrawerHelper();

    ScreensNavigator getScreensNavigator();

    DialogsManager getDialogsManager();

    DialogsEventBus getDialogsEventBus();
    StackoverflowApi getStackoverflowApi();
}
