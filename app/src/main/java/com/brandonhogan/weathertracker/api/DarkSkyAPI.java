package com.brandonhogan.weathertracker.api;

import com.brandonhogan.weathertracker.model.DarkSkyResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface DarkSkyAPI {

    @GET("{latitude},{longitude}?exclude=minutely&units=si")
    Observable<DarkSkyResponse> getForecast(
                                            @Path(value = "latitude", encoded = true) double latitude,
                                            @Path(value = "longitude", encoded = true) double longitude);
}
