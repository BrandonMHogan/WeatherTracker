package com.brandonhogan.weathertracker.ui.views;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bluelinelabs.conductor.Conductor;
import com.bluelinelabs.conductor.Router;
import com.bluelinelabs.conductor.RouterTransaction;
import com.brandonhogan.AppController;
import com.brandonhogan.weathertracker.R;
import com.brandonhogan.weathertracker.ui.contracts.MainActivityContract;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainActivityContract.View, OnTabSelectListener {

    private static final String TAG = MainActivity.class.getName();
    private static final int MY_PERMISSION_ACCESS_COURSE_LOCATION = 1;
    private static final int MY_PERMISSION_ACCESS_FINE_LOCATION = 2;

    @Inject
    MainActivityContract.Presenter presenter;

    @Bind(R.id.controller_container)
    ViewGroup container;

    @Bind(R.id.bottomBar)
    BottomBar bottomBar;

    private Router router;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        AppController.getViewComponent().inject(this);
        presenter.setView(this);

        router = Conductor.attachRouter(this, container, savedInstanceState);
        if (!router.hasRootController()) {
            router.setRoot(RouterTransaction.with(new HomeController()));
        }

        bottomBar.setOnTabSelectListener(this);
    }

    @Override
    public void onBackPressed() {
        if (!router.handleBack()) {
            super.onBackPressed();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {
            case MY_PERMISSION_ACCESS_COURSE_LOCATION: {
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d(TAG, "onRequestPermissionsResult: Course Location permission accepted");
                }
                else {
                    Toast.makeText(this, "Permissions needed to retreive forcast.", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "onRequestPermissionsResult: Course Location permission declined");
                }
            }
            case MY_PERMISSION_ACCESS_FINE_LOCATION: {
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d(TAG, "onRequestPermissionsResult: Fine Location permission accepted");
                }
                else {
                    Toast.makeText(this, "Permissions needed to retreive forcast.", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "onRequestPermissionsResult: Fine Location permission declined");
                }
            }
        }
    }

    // Check to make sure that you have the correct permissions on start, since the app needs your location to work correctly
    @Override
    protected void onResume() {
        super.onResume();
        if ( ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED ) {
            ActivityCompat.requestPermissions(this, new String[] {  android.Manifest.permission.ACCESS_COARSE_LOCATION  },
                    MY_PERMISSION_ACCESS_COURSE_LOCATION );
        }
        else if ( ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ) {
            ActivityCompat.requestPermissions(this, new String[] {  android.Manifest.permission.ACCESS_FINE_LOCATION  },
                    MY_PERMISSION_ACCESS_FINE_LOCATION );
        }
        else {
            presenter.onAttach();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDetach();
    }

    @Override
    public void onTabSelected(@IdRes int tabId) {
        switch (tabId) {
            case R.id.navigation_home:
                router.setRoot(RouterTransaction.with(new HomeController()));
                break;
            case R.id.navigation_about:
                router.setRoot(RouterTransaction.with(new AboutController()));
                break;
        }
    }
}
