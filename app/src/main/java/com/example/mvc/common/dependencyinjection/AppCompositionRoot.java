package com.example.mvc.common.dependencyinjection;

import android.app.Application;

import com.example.mvc.common.Constants;
import com.example.mvc.networking.StackoverflowApi;
import com.example.mvc.screens.common.dialogs.DialogsEventBus;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AppCompositionRoot {
    private Retrofit mRetrofit;
    private DialogsEventBus dialogsEventBus;

    private final Application application;

    public AppCompositionRoot(Application application) {
        this.application = application;
    }

    public Application getApplication(){
        return application;
    }

    public StackoverflowApi getStackoverflowApi() {
        return getRetrofit().create(StackoverflowApi.class);
    }

    private Retrofit getRetrofit(){
        if(mRetrofit == null){
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return mRetrofit;
    }

    public DialogsEventBus getDialogsEventBus() {
        if(dialogsEventBus == null){
            dialogsEventBus = new DialogsEventBus();
        }
        return dialogsEventBus;
    }
}
