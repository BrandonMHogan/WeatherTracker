package com.brandonhogan.weathertracker.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DarkSkyResponse {

    @SerializedName("latitude")
    @Expose
    private Double latitude;
    @SerializedName("longitude")
    @Expose
    private Double longitude;
    @SerializedName("timezone")
    @Expose
    private String timezone;
    @SerializedName("currently")
    @Expose
    private DarkSkyCurrentlyResponse currently;

    @SerializedName("alerts")
    @Expose
    private List<DarkSkyAlertResponse> alerts = null;

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public DarkSkyCurrentlyResponse getCurrently() {
        return currently;
    }

    public void setCurrently(DarkSkyCurrentlyResponse currently) {
        this.currently = currently;
    }

    public List<DarkSkyAlertResponse> getAlerts() {
        return alerts;
    }

    public void setAlerts(List<DarkSkyAlertResponse> alerts) {
        this.alerts = alerts;
    }
}
