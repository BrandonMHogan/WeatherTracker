package com.brandonhogan.weathertracker.di.module;

import com.brandonhogan.weathertracker.ui.contracts.MainActivityContract;
import com.brandonhogan.weathertracker.ui.presenters.MainActivityPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class PresenterModule {

    @Provides
    @Singleton
    MainActivityContract.Presenter providesMainActivityPresenter() {
        return new MainActivityPresenter();
    }

}
