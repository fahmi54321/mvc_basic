package com.example.mvc.common.dependencyinjection.app;

import android.app.Application;

import com.example.mvc.common.Constants;
import com.example.mvc.networking.StackoverflowApi;
import com.example.mvc.screens.common.dialogs.DialogsEventBus;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class AppModule {

    private final Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    public Application getApplication(){
        return application;
    }

    @Provides
    public StackoverflowApi getStackoverflowApi(Retrofit retrofit) {
        return retrofit.create(StackoverflowApi.class);
    }

    @AppScope
    @Provides
    public Retrofit getRetrofit(){
        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @AppScope
    @Provides
    public DialogsEventBus getDialogsEventBus() {
        return new DialogsEventBus();
    }
}
