package com.example.mvc.common.dependencyinjection.app;

import android.app.Application;

import com.example.mvc.networking.StackoverflowApi;
import com.example.mvc.screens.common.dialogs.DialogsEventBus;

import dagger.Component;

@AppScope
@Component(modules = {AppModule.class})
public interface AppComponent {
    DialogsEventBus dialogsEventBus();

    StackoverflowApi stackoverflowApi();

    Application application();
}
