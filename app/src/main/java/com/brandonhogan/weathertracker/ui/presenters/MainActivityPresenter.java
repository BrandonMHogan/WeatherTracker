package com.brandonhogan.weathertracker.ui.presenters;

import com.brandonhogan.AppController;
import com.brandonhogan.weathertracker.ui.contracts.MainActivityContract;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class MainActivityPresenter implements MainActivityContract.Presenter {

    private static final String TAG = MainActivityPresenter.class.getName();

    private MainActivityContract.View view;

    @Inject
    public MainActivityPresenter() {
        AppController.getMainComponent().inject(this);
    }

    @Override
    public void onAttach() {

    }

    @Override
    public void onDetach() {
        view = null;
    }

    @Override
    public void setView(MainActivityContract.View view) {
        this.view = view;
    }
}
