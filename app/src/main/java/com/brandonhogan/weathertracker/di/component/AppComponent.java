package com.brandonhogan.weathertracker.di.component;

import com.brandonhogan.weathertracker.di.module.AppModule;
import com.brandonhogan.weathertracker.di.module.DarkSkyModule;
import com.brandonhogan.weathertracker.di.module.NetworkModule;
import com.brandonhogan.weathertracker.ui.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, NetworkModule.class, DarkSkyModule.class})
public interface AppComponent {

    void inject(MainActivity activity);
}
