package com.brandonhogan.weathertracker.managers;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class GPSManager extends Service implements LocationListener {

    private static final String TAG = GPSManager.class.getName();
    private static final long MIN_TIME_UPDATES = 1000 * 60; // 1 min intervals
    private static final long MIN_DISTANCE_CHANGE_UPDATES = 10; // 10 meters

    private Context context;
    private final PublishSubject<Location> locationBus;

    public GPSManager(Context context) {
        locationBus = PublishSubject.create();
        this.context = context;
        update();
    }

    // When the location updates, the subject's onNext is called, which will trigger all
    // observers on this observable.
    public Observable<Location> locationUpdated() {
        return locationBus;
    }

    @SuppressWarnings({"MissingPermission"})
    public void update() {

        try {
            LocationManager locationManager = (LocationManager) context.getSystemService(LOCATION_SERVICE);
            List<String> providers = locationManager.getProviders(true);

            for (String provider : providers) {
                locationManager.requestLocationUpdates(provider, MIN_TIME_UPDATES, MIN_DISTANCE_CHANGE_UPDATES, this);
                Location location = locationManager.getLastKnownLocation(provider);

                if (location != null) {
                    Log.d(TAG, "update: " + provider + " is enabled. Using its location");
                    updateLocation(location);
                    break;
                }
            }
        }
        catch (Exception ex) {
            Log.e(TAG, "getLocation: ", ex);
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        updateLocation(location);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void updateLocation(Location location) {
        if (location != null)
            locationBus.onNext(location);
    }
}
