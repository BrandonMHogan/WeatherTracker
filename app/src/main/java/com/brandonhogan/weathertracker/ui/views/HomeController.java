package com.brandonhogan.weathertracker.ui.views;

import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bluelinelabs.conductor.Controller;
import com.brandonhogan.AppController;
import com.brandonhogan.weathertracker.R;
import com.brandonhogan.weathertracker.model.CurrentlyResponse;
import com.brandonhogan.weathertracker.model.DarkSkyResponse;
import com.brandonhogan.weathertracker.ui.contracts.HomeControllerContract;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HomeController extends Controller implements HomeControllerContract.View {

    private static final String TAG = HomeController.class.getName();
    private static final int MY_PERMISSION_ACCESS_COURSE_LOCATION = 1;
    private static final int MY_PERMISSION_ACCESS_FINE_LOCATION = 2;

    @Inject
    HomeControllerContract.Presenter presenter;

    @Bind(R.id.progressBar)
    ProgressBar progressBar;

    @Bind(R.id.loading)
    TextView loadingText;

    @Bind(R.id.summary)
    TextView summaryText;

    @Bind(R.id.temperature)
    TextView temperatureText;

    @Bind(R.id.temperature_apparent)
    TextView temperatureApparentText;

    @Bind(R.id.precipitation)
    TextView precipitationText;

    @Bind(R.id.humidity)
    TextView humidityText;

    @Bind(R.id.windSpeed)
    TextView windSpeedText;

    @Bind(R.id.iconImageView)
    ImageView iconImage;

    private Toast toast;

    @SuppressLint("ShowToast")
    @NonNull
    protected View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        View view = inflater.inflate(R.layout.controller_home, container, false);

        ButterKnife.bind(this, view);
        AppController.getViewComponent().inject(this);
        setHasOptionsMenu(true);

        toast = Toast.makeText(getActivity(), null, Toast.LENGTH_SHORT);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.appbar_menu, menu);
    }

    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh:
                progressBar.setVisibility(View.VISIBLE);
                presenter.onRefresh();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.d(TAG, "onRequestPermissionsResult: ");
        switch (requestCode) {
            case MY_PERMISSION_ACCESS_COURSE_LOCATION: {
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d(TAG, "onRequestPermissionsResult: Course Location permission accepted");
                    presenter.onAttach();
                }
                else {
                    Toast.makeText(getActivity(), "Permissions needed to retreive forcast.", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "onRequestPermissionsResult: Course Location permission declined");
                    permissionDenied();
                }
            }
        }
    }

    // Check to make sure that you have the correct permissions on start, since the app needs your location to work correctly
    @Override
    protected void onAttach(@NonNull View view) {
        super.onAttach(view);
        presenter.setView(this);

        if (getActivity() == null)
            return;

        if ( ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED ) {
            this.requestPermissions(new String[] {  android.Manifest.permission.ACCESS_COARSE_LOCATION  },
                    MY_PERMISSION_ACCESS_COURSE_LOCATION);
        }
        else {
            presenter.onAttach();
        }
    }

    @Override
    protected void onDetach(@NonNull View view) {
        super.onDetach(view);
        presenter.onDetach();
    }

    @Override
    protected void onSaveViewState(@NonNull View view, @NonNull Bundle outState) {
        outState.putBundle(presenter.getStateKey(), presenter.getState());
        super.onSaveViewState(view, outState);
    }

    @Override
    protected void onRestoreViewState(@NonNull View view, @NonNull Bundle savedViewState) {

        if(!savedViewState.isEmpty()) {
            presenter.setView(this);
            presenter.setState(savedViewState);
        }

        super.onRestoreViewState(view, savedViewState);
    }

    @Override
    public void onLocationFound() {
        loadingText.setText("");
    }

    @Override
    public void onLoad(DarkSkyResponse response) {

        if(getActivity() == null)
            return;

        getActivity().setTitle(response.getLocation());

        if (response.getCurrently() != null) {

            CurrentlyResponse currently = response.getCurrently();

            summaryText.setText(currently.getSummary());
            temperatureText.setText(getActivity().getString(R.string.temperature, currently.getTemperature().toString()));
            temperatureApparentText.setText(getActivity().getString(R.string.temperature_apparent, currently.getApparentTemperature().toString()));
            precipitationText.setText(getActivity().getString(R.string.precipitation, Double.toString(currently.getPrecipProbabilityPercent())));
            humidityText.setText(getActivity().getString(R.string.humidity, Double.toString(currently.getHumidityPercent())));
            windSpeedText.setText(getActivity().getString(R.string.wind_speed, Integer.toString(currently.getWindSpeedMPH())));

            iconImage.setImageResource(currently.getIconImage());

        }
        progressBar.setVisibility(View.GONE);
        showToast(R.string.forecast_loaded);
    }

    @Override
    public void onLoadFail() {
        showToast(R.string.forecast_failed);
    }

    private void showToast(int msg) {
        toast.setText(msg);
        toast.show();
    }

    private void permissionDenied() {
        loadingText.setText(R.string.loading_dialog_denied);
        progressBar.setVisibility(View.GONE);
    }
}
