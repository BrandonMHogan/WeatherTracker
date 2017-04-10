package com.brandonhogan.weathertracker.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.brandonhogan.AppController;
import com.brandonhogan.weathertracker.R;
import com.brandonhogan.weathertracker.api.DarkSkyAPI;
import com.brandonhogan.weathertracker.di.component.AppComponent;
import com.brandonhogan.weathertracker.model.DarkSkyResponse;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();

    @Inject
    DarkSkyAPI darkSkyAPI;

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppController.getComponent().inject(this);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    protected void onStart() {
        super.onStart();

        long lon = (long)37.8267;
        long lan = (long)-122.4233;

        darkSkyAPI.getForecast(lon,lan)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<DarkSkyResponse>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull DarkSkyResponse darkSkyResponse) throws Exception {
                        onLoad(darkSkyResponse);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull Throwable throwable) throws Exception {
                        onError(throwable);
                    }
                });
//                .subscribe(trendsResponse -> load(trendsResponse),
//                        throwable -> view.error(throwable)));
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    private void onLoad(DarkSkyResponse response) {
        Log.d(TAG, "onLoad: ");
    }

    private void onError(Throwable e) {
        Log.d(TAG, "onLoad: ");
    }
}
