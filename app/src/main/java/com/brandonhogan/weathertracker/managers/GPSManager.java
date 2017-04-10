package com.brandonhogan.weathertracker.managers;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.brandonhogan.AppController;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by Brandon on 4/10/2017.
 * Description :
 */

public class GPSManager extends Service implements LocationListener {

    private static final String TAG = GPSManager.class.getName();
    private static final long MIN_TIME_UPDATES = 1000 * 60; // 1 min intervals
    private static final long MIN_DISTANCE_CHANGE_UPDATES = 10; // 10 meters

    @Inject
    Context context;

    private final PublishSubject<Location> locationBus;
    private LocationManager locationManager;


    public GPSManager() {
        AppController.getComponent().inject(this);
        locationBus = PublishSubject.create();
        init();
    }

    // When the location updates, the subject's onNext is called, which will trigger all
    // observers on this observable.
    public Observable<Location> locationUpdated() {
        return locationBus;
    }

    private void init() {

        try {
            locationManager = (LocationManager) context.getSystemService(LOCATION_SERVICE);

            boolean isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

            if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                Log.d(TAG, "getLocation: GPS is enabled");
                requestLocationUpdate(LocationManager.GPS_PROVIDER);
            }
            else if(locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
                Log.d(TAG, "getLocation: Network is enabled");
                requestLocationUpdate(LocationManager.NETWORK_PROVIDER);
            }
            else {
                Log.d(TAG, "getLocation: Both GPS and Network disabled. Cannot request location update");
            }

        }
        catch (Exception ex) {
            Log.e(TAG, "getLocation: ", ex);
        }
    }

    // The permission is checked before referencing the GPS Manager.
    @SuppressWarnings({"MissingPermission"})
    private void requestLocationUpdate(String providerInfo) {
        locationManager.requestLocationUpdates(providerInfo, MIN_TIME_UPDATES, MIN_DISTANCE_CHANGE_UPDATES, this);

        if (locationManager != null)
            updateLocation(locationManager.getLastKnownLocation(providerInfo));
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
        locationBus.onNext(location);
    }
}
