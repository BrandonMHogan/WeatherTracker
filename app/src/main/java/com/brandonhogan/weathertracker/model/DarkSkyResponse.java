package com.brandonhogan.weathertracker.model;

public class DarkSkyResponse {

    private long latitude;
    private long longitude;
    private String timeZone;

    public DarkSkyResponse() {
    }

    public DarkSkyResponse(long latitude, long longitude, String timeZone) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.timeZone = timeZone;
    }

    public long getLatitude() {
        return latitude;
    }

    public void setLatitude(long latitude) {
        this.latitude = latitude;
    }

    public long getLongitude() {
        return longitude;
    }

    public void setLongitude(long longitude) {
        this.longitude = longitude;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }
}
