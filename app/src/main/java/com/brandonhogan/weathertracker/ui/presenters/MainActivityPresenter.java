package com.brandonhogan.weathertracker.ui.presenters;

import android.util.Log;

import com.brandonhogan.AppController;
import com.brandonhogan.weathertracker.api.DarkSkyAPI;
import com.brandonhogan.weathertracker.model.DarkSkyResponse;
import com.brandonhogan.weathertracker.ui.contracts.MainActivityContract;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class MainActivityPresenter implements MainActivityContract.Presenter {

    private static final String TAG = MainActivityPresenter.class.getName();

    @Inject
    DarkSkyAPI darkSkyAPI;

    private MainActivityContract.View view;

    public MainActivityPresenter() {
        AppController.getComponent().inject(this);
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

        long lon = (long)37.8267;
        long lan = (long)-122.4233;

        darkSkyAPI.getForecast(lon,lan)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<DarkSkyResponse>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull DarkSkyResponse darkSkyResponse) throws Exception {
                        onForecastLoad(darkSkyResponse);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull Throwable throwable) throws Exception {
                        onForecastError(throwable);
                    }
                });
    }

    @Override
    public void onDestroy() {
        view = null;
    }

    @Override
    public void setView(MainActivityContract.View view) {
        this.view = view;
    }

    private void onForecastLoad(DarkSkyResponse response) {
        Log.d(TAG, "onLoad: ");
    }

    private void onForecastError(Throwable e) {
        Log.d(TAG, "onLoad: ");
    }
}
