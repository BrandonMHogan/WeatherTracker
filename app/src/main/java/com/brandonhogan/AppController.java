package com.brandonhogan;

import android.app.Application;

import com.brandonhogan.weathertracker.di.component.AppComponent;
import com.brandonhogan.weathertracker.di.component.DaggerAppComponent;
import com.brandonhogan.weathertracker.di.module.AppModule;
import com.brandonhogan.weathertracker.di.module.DarkSkyModule;
import com.brandonhogan.weathertracker.di.module.NetworkModule;

public class AppController extends Application {

    private static AppComponent component;

    @Override public void onCreate() {
        super.onCreate();
        component = DaggerAppComponent.builder()
                .darkSkyModule(new DarkSkyModule())
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule())
                .build();
    }

    public static AppComponent getComponent() {
        return component;
    }
}
