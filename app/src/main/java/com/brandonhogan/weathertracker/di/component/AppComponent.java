package com.brandonhogan.weathertracker.di.component;

import com.brandonhogan.weathertracker.di.module.AppModule;
import com.brandonhogan.weathertracker.di.module.DarkSkyModule;
import com.brandonhogan.weathertracker.di.module.ManagerModule;
import com.brandonhogan.weathertracker.di.module.NetworkModule;
import com.brandonhogan.weathertracker.managers.GPSManager;
import com.brandonhogan.weathertracker.ui.presenters.MainActivityPresenter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, NetworkModule.class, DarkSkyModule.class, ManagerModule.class})
public interface AppComponent {

    // Presenters
    void inject(MainActivityPresenter presenter);

    // Managers
    void inject(GPSManager manager);
}
