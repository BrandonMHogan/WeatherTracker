package com.brandonhogan.weathertracker.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class DarkSkyResponse implements Parcelable {

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
    private CurrentlyResponse currently;

    @SerializedName("alerts")
    @Expose
    private List<AlertResponse> alerts = null;

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

    public CurrentlyResponse getCurrently() {
        return currently;
    }

    public void setCurrently(CurrentlyResponse currently) {
        this.currently = currently;
    }

    public List<AlertResponse> getAlerts() {
        return alerts;
    }

    public void setAlerts(List<AlertResponse> alerts) {
        this.alerts = alerts;
    }

    public String getLocation() {
        int index = timezone.indexOf("/");

        if(timezone.length() > index + 1)
            return timezone.substring(index + 1);

        return timezone;
    }

    protected DarkSkyResponse(Parcel in) {
        latitude = in.readByte() == 0x00 ? null : in.readDouble();
        longitude = in.readByte() == 0x00 ? null : in.readDouble();
        timezone = in.readString();
        currently = (CurrentlyResponse) in.readValue(CurrentlyResponse.class.getClassLoader());
        if (in.readByte() == 0x01) {
            alerts = new ArrayList<>();
            in.readList(alerts, AlertResponse.class.getClassLoader());
        } else {
            alerts = null;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (latitude == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeDouble(latitude);
        }
        if (longitude == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeDouble(longitude);
        }
        dest.writeString(timezone);
        dest.writeValue(currently);
        if (alerts == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(alerts);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<DarkSkyResponse> CREATOR = new Parcelable.Creator<DarkSkyResponse>() {
        @Override
        public DarkSkyResponse createFromParcel(Parcel in) {
            return new DarkSkyResponse(in);
        }

        @Override
        public DarkSkyResponse[] newArray(int size) {
            return new DarkSkyResponse[size];
        }
    };
}
