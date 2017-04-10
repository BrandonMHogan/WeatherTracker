package com.brandonhogan.weathertracker.di.module;

import com.brandonhogan.weathertracker.api.DarkSkyAPI;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class DarkSkyModule {

    @Provides
    @Singleton
    DarkSkyAPI providesRetrofit(Retrofit retrofit) {
        return retrofit.create(DarkSkyAPI.class);
    }
}
