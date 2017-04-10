package com.brandonhogan.weathertracker.ui.views;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.brandonhogan.AppController;
import com.brandonhogan.weathertracker.R;
import com.brandonhogan.weathertracker.ui.contracts.MainActivityContract;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements MainActivityContract.View, BottomNavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = MainActivity.class.getName();
    private static final int MY_PERMISSION_ACCESS_COURSE_LOCATION = 1;

    @Inject
    MainActivityContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppController.getViewComponent().inject(this);
        presenter.setView(this);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);
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
        }
    }

    // Check to make sure that you have the correct permissions on start, since the app needs your location to work correctly
    @Override
    protected void onResume() {
        super.onResume();
        if ( ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ) {
            ActivityCompat.requestPermissions(this, new String[] {  android.Manifest.permission.ACCESS_FINE_LOCATION  },
                    MY_PERMISSION_ACCESS_COURSE_LOCATION );
        }
        else {
            presenter.onResume();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_home:
                return true;
            case R.id.navigation_dashboard:
                return true;
            case R.id.navigation_notifications:
                return true;
        }
        return false;
    }
}
