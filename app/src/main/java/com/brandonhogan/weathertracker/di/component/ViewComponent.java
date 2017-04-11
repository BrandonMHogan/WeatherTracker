package com.brandonhogan.weathertracker.di.component;

import com.brandonhogan.weathertracker.di.module.PresenterModule;
import com.brandonhogan.weathertracker.ui.views.HomeController;
import com.brandonhogan.weathertracker.ui.views.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = PresenterModule.class)
public interface ViewComponent {

    void inject(MainActivity activity);
    void inject(HomeController controller);
}
