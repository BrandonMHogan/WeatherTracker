package com.brandonhogan.weathertracker.di.module;

import android.app.Application;
import android.content.Context;

import com.brandonhogan.weathertracker.managers.GPSManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module
public class AppModule {

    private Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public Application providesApplication() {
        return application;
    }

    @Provides
    @Singleton
    public Context providesContext() {
        return application.getApplicationContext();
    }

    @Provides
    @Singleton
    GPSManager providesManager(Context context) {
        return new GPSManager(context);
    }
}
