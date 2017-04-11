package com.brandonhogan.weathertracker.ui.presenters;

import android.location.Location;
import android.os.Bundle;
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

    private static final String STATE_BUNDLE = "homeControllerState";
    private static final String PARCABLE_RESPONSE = "parcableResponse";

    @Inject
    DarkSkyAPI darkSkyAPI;

    @Inject
    GPSManager gpsManager;

    private HomeControllerContract.View view;
    private Disposable locationDisposible;
    private DarkSkyResponse response;

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

    @Override
    public void onRefresh() {
        gpsManager.update();
    }

    @Override
    public Bundle getState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(PARCABLE_RESPONSE, response);
        return bundle;
    }

    @Override
    public String getStateKey() {
        return STATE_BUNDLE;
    }

    @Override
    public void setState(Bundle state) {
        Log.d(TAG, "setState: State is being restored...");

        try {
            setView(view);
            Bundle bundle = state.getBundle(STATE_BUNDLE);
            DarkSkyResponse stateResponse = bundle.getParcelable(PARCABLE_RESPONSE);

            onForecastLoad(stateResponse);
        }
        catch (Exception ex) {
            Log.e(TAG, "setState: State failed to restore", ex);
        }
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
        this.response = response;

        if (response != null)
            view.onLoad(response);
    }

    private void onForecastError(Throwable e) {
        Log.e(TAG, "onForecastError: ", e);
        view.onLoadFail();
    }

}
