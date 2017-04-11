package com.brandonhogan.weathertracker.di.component;

import com.brandonhogan.weathertracker.di.module.AppModule;
import com.brandonhogan.weathertracker.di.module.DarkSkyModule;
import com.brandonhogan.weathertracker.di.module.NetworkModule;
import com.brandonhogan.weathertracker.managers.GPSManager;
import com.brandonhogan.weathertracker.ui.presenters.HomeControllerPresenter;
import com.brandonhogan.weathertracker.ui.presenters.MainActivityPresenter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, NetworkModule.class, DarkSkyModule.class})
public interface AppComponent {

    // Presenters
    void inject(MainActivityPresenter presenter);
    void inject(HomeControllerPresenter presenter);

    // Managers
    void inject(GPSManager manager);
}
