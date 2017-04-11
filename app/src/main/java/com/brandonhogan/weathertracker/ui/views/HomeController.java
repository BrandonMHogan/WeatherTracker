package com.brandonhogan.weathertracker.ui.views;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bluelinelabs.conductor.Controller;
import com.brandonhogan.AppController;
import com.brandonhogan.weathertracker.R;
import com.brandonhogan.weathertracker.model.DarkSkyCurrentlyResponse;
import com.brandonhogan.weathertracker.model.DarkSkyResponse;
import com.brandonhogan.weathertracker.ui.contracts.HomeControllerContract;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HomeController extends Controller implements HomeControllerContract.View {

    @Inject
    HomeControllerContract.Presenter presenter;

    @Bind(R.id.loading_layout)
    ConstraintLayout loadingLayout;

    @Bind(R.id.summary)
    TextView summaryText;

    @Bind(R.id.temperature)
    TextView temperatureText;

    @Bind(R.id.temperature_apparent)
    TextView temperatureApparentText;

    @Bind(R.id.precipitation)
    TextView precipitationText;

    @Bind(R.id.iconImageView)
    ImageView iconImage;

    protected View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        View view = inflater.inflate(R.layout.controller_home, container, false);

        ButterKnife.bind(this, view);
        AppController.getViewComponent().inject(this);
        setHasOptionsMenu(true);

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
                presenter.onRefresh();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onAttach(@NonNull View view) {
        super.onAttach(view);
        presenter.setView(this);
        presenter.onAttach();
    }

    @Override
    protected void onDetach(@NonNull View view) {
        super.onDetach(view);
        presenter.onDetach();
    }

    @Override
    public void onLoad(DarkSkyResponse response) {
        if (response.getCurrently() != null) {

            DarkSkyCurrentlyResponse currently = response.getCurrently();

            summaryText.setText(currently.getSummary());
            temperatureText.setText(getActivity().getString(R.string.temperature, currently.getTemperature().toString()));
            temperatureApparentText.setText(getActivity().getString(R.string.temperature_apparent, currently.getApparentTemperature().toString()));

            //String percentage = Double.toString(currently.getPrecipProbability() * 100);
            precipitationText.setText(getActivity().getString(R.string.precipitation, Double.toString(currently.getPrecipProbability() * 100)));

            switch (response.getCurrently().getIcon().toLowerCase()) {
                case "clear-day":
                    iconImage.setImageResource(R.drawable.ic_sun);
                    break;
                case "clear-night":
                    iconImage.setImageResource(R.drawable.ic_moon);
                    break;
                case "partly-cloudy-day":
                    iconImage.setImageResource(R.drawable.ic_cloud_sun);
                    break;
                case "partly-cloudy-night":
                    iconImage.setImageResource(R.drawable.ic_cloud_moon);
                    break;
                case "cloudy":
                    iconImage.setImageResource(R.drawable.ic_cloud);
                    break;
                case "rain":
                    iconImage.setImageResource(R.drawable.ic_cloud_drizzle_alt);
                    break;
                case "sleet":
                    iconImage.setImageResource(R.drawable.ic_cloud_hail_alt);
                    break;
                case "snow":
                    iconImage.setImageResource(R.drawable.ic_cloud_snow);
                    break;
                case "wind":
                    iconImage.setImageResource(R.drawable.ic_wind);
                    break;
                case "fog":
                    iconImage.setImageResource(R.drawable.ic_cloud_fog);
            }

        }
        loadingLayout.setVisibility(View.GONE);
        Toast.makeText(getActivity(), "Forecast Refreshed", Toast.LENGTH_SHORT).show();
    }
}
