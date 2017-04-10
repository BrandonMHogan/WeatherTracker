package com.brandonhogan;

import android.app.Application;

import com.brandonhogan.weathertracker.di.component.AppComponent;
import com.brandonhogan.weathertracker.di.component.DaggerAppComponent;
import com.brandonhogan.weathertracker.di.component.DaggerViewComponent;
import com.brandonhogan.weathertracker.di.component.ViewComponent;
import com.brandonhogan.weathertracker.di.module.AppModule;
import com.brandonhogan.weathertracker.di.module.DarkSkyModule;
import com.brandonhogan.weathertracker.di.module.NetworkModule;
import com.brandonhogan.weathertracker.di.module.PresenterModule;

public class AppController extends Application {

    private static AppComponent mainComponent;
    private static ViewComponent viewComponent;

    @Override public void onCreate() {
        super.onCreate();
        mainComponent = DaggerAppComponent.builder()
                .darkSkyModule(new DarkSkyModule())
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule())
                .build();

        viewComponent = DaggerViewComponent.builder()
                .presenterModule(new PresenterModule())
                .build();

    }

    public static AppComponent getMainComponent() {
        return mainComponent;
    }

    public static ViewComponent getViewComponent() {
        return viewComponent;
    }
}
