package com.brandonhogan.weathertracker.di.module;

import com.brandonhogan.weathertracker.managers.GPSManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ManagerModule {

    @Provides
    @Singleton
    GPSManager providesManager(GPSManager manager) {
        return new GPSManager();
    }
}
