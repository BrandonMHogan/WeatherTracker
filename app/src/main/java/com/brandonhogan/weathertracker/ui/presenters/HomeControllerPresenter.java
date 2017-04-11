package com.brandonhogan.weathertracker.ui.presenters;

import android.location.Location;
import android.util.Log;

import com.brandonhogan.AppController;
import com.brandonhogan.weathertracker.api.DarkSkyAPI;
import com.brandonhogan.weathertracker.managers.GPSManager;
import com.brandonhogan.weathertracker.model.DarkSkyResponse;
import com.brandonhogan.weathertracker.ui.contracts.HomeControllerContract;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

@Singleton
public class HomeControllerPresenter implements HomeControllerContract.Presenter {

    private static final String TAG = HomeControllerPresenter.class.getName();

    @Inject
    DarkSkyAPI darkSkyAPI;

    @Inject
    GPSManager gpsManager;

    private HomeControllerContract.View view;
    private Disposable locationDisposible;

    @Inject
    public HomeControllerPresenter() {
        AppController.getMainComponent().inject(this);
    }

    @Override
    public void onAttach() {
        if (locationDisposible == null || locationDisposible.isDisposed())
            updateLocation();
    }

    @Override
    public void onDetach() {
        view = null;
        locationDisposible.dispose();
    }

    @Override
    public void setView(HomeControllerContract.View view) {
        this.view = view;
    }

    // Will attach to the subject observer in the gps manager,
    // and then call an update
    private void updateLocation() {
        locationDisposible = gpsManager.locationUpdated()
                .subscribe(new Consumer<Location>() {
                    @Override
                    public void accept(@NonNull Location location) throws Exception {
                        getForecast(location.getLatitude(), location.getLongitude());
                    }
                });

        gpsManager.update();
    }

    // Makes the call to the forecast API
    private void getForecast(double latitude, double longitude) {
        darkSkyAPI.getForecast((long)latitude, (long)longitude)
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

    private void onForecastLoad(DarkSkyResponse response) {
        view.onLoad(response);
    }

    private void onForecastError(Throwable e) {
        Log.d(TAG, "onForecastError: ", e);
    }

}
