package com.brandonhogan.weathertracker.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.brandonhogan.weathertracker.R;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class CurrentlyResponse implements Parcelable {

    @SerializedName("time")
    @Expose
    private Integer time;
    @SerializedName("summary")
    @Expose
    private String summary;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("nearestStormDistance")
    @Expose
    private Integer nearestStormDistance;
    @SerializedName("precipIntensity")
    @Expose
    private Double precipIntensity;
    @SerializedName("precipIntensityError")
    @Expose
    private Double precipIntensityError;
    @SerializedName("precipProbability")
    @Expose
    private Integer precipProbability;
    @SerializedName("precipType")
    @Expose
    private String precipType;
    @SerializedName("temperature")
    @Expose
    private Double temperature;
    @SerializedName("apparentTemperature")
    @Expose
    private Double apparentTemperature;
    @SerializedName("dewPoint")
    @Expose
    private Double dewPoint;
    @SerializedName("humidity")
    @Expose
    private Double humidity;
    @SerializedName("windSpeed")
    @Expose
    private Double windSpeed;
    @SerializedName("windBearing")
    @Expose
    private Integer windBearing;
    @SerializedName("visibility")
    @Expose
    private Double visibility;
    @SerializedName("cloudCover")
    @Expose
    private Double cloudCover;
    @SerializedName("pressure")
    @Expose
    private Double pressure;
    @SerializedName("ozone")
    @Expose
    private Double ozone;

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getNearestStormDistance() {
        return nearestStormDistance;
    }

    public void setNearestStormDistance(Integer nearestStormDistance) {
        this.nearestStormDistance = nearestStormDistance;
    }

    public Double getPrecipIntensity() {
        return precipIntensity;
    }

    public void setPrecipIntensity(Double precipIntensity) {
        this.precipIntensity = precipIntensity;
    }

    public Double getPrecipIntensityError() {
        return precipIntensityError;
    }

    public void setPrecipIntensityError(Double precipIntensityError) {
        this.precipIntensityError = precipIntensityError;
    }

    public Integer getPrecipProbability() {
        return precipProbability;
    }

    public void setPrecipProbability(Integer precipProbability) {
        this.precipProbability = precipProbability;
    }

    public String getPrecipType() {
        return precipType;
    }

    public void setPrecipType(String precipType) {
        this.precipType = precipType;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Double getApparentTemperature() {
        return apparentTemperature;
    }

    public void setApparentTemperature(Double apparentTemperature) {
        this.apparentTemperature = apparentTemperature;
    }

    public Double getDewPoint() {
        return dewPoint;
    }

    public void setDewPoint(Double dewPoint) {
        this.dewPoint = dewPoint;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    public Double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(Double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public Integer getWindBearing() {
        return windBearing;
    }

    public void setWindBearing(Integer windBearing) {
        this.windBearing = windBearing;
    }

    public Double getVisibility() {
        return visibility;
    }

    public void setVisibility(Double visibility) {
        this.visibility = visibility;
    }

    public Double getCloudCover() {
        return cloudCover;
    }

    public void setCloudCover(Double cloudCover) {
        this.cloudCover = cloudCover;
    }

    public Double getPressure() {
        return pressure;
    }

    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    public Double getOzone() {
        return ozone;
    }

    public void setOzone(Double ozone) {
        this.ozone = ozone;
    }

    public int getIconImage() {
        switch (icon.toLowerCase()) {
            case "clear-day":
                return R.drawable.ic_sun;
            case "clear-night":
                return R.drawable.ic_moon;
            case "partly-cloudy-day":
                return R.drawable.ic_cloud_sun;
            case "partly-cloudy-night":
                return R.drawable.ic_cloud_moon;
            case "cloudy":
                return R.drawable.ic_cloud;
            case "rain":
                return R.drawable.ic_cloud_drizzle_alt;
            case "sleet":
                return R.drawable.ic_cloud_hail_alt;
            case "snow":
                return R.drawable.ic_cloud_snow;
            case "wind":
                return R.drawable.ic_wind;
            case "fog":
                return R.drawable.ic_cloud_fog;
            default:
                return R.drawable.ic_sun;
        }
    }

    public int getWindSpeedMPH() {
        return ((int) (windSpeed * 2.236936));
    }

    public double getPrecipProbabilityPercent() {
        return ((double) (precipProbability * 100));
    }

    public double getHumidityPercent() {
        return (humidity * 100);
    }


    protected CurrentlyResponse(Parcel in) {
        time = in.readByte() == 0x00 ? null : in.readInt();
        summary = in.readString();
        icon = in.readString();
        nearestStormDistance = in.readByte() == 0x00 ? null : in.readInt();
        precipIntensity = in.readByte() == 0x00 ? null : in.readDouble();
        precipIntensityError = in.readByte() == 0x00 ? null : in.readDouble();
        precipProbability = in.readByte() == 0x00 ? null : in.readInt();
        precipType = in.readString();
        temperature = in.readByte() == 0x00 ? null : in.readDouble();
        apparentTemperature = in.readByte() == 0x00 ? null : in.readDouble();
        dewPoint = in.readByte() == 0x00 ? null : in.readDouble();
        humidity = in.readByte() == 0x00 ? null : in.readDouble();
        windSpeed = in.readByte() == 0x00 ? null : in.readDouble();
        windBearing = in.readByte() == 0x00 ? null : in.readInt();
        visibility = in.readByte() == 0x00 ? null : in.readDouble();
        cloudCover = in.readByte() == 0x00 ? null : in.readDouble();
        pressure = in.readByte() == 0x00 ? null : in.readDouble();
        ozone = in.readByte() == 0x00 ? null : in.readDouble();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (time == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(time);
        }
        dest.writeString(summary);
        dest.writeString(icon);
        if (nearestStormDistance == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(nearestStormDistance);
        }
        if (precipIntensity == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeDouble(precipIntensity);
        }
        if (precipIntensityError == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeDouble(precipIntensityError);
        }
        if (precipProbability == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(precipProbability);
        }
        dest.writeString(precipType);
        if (temperature == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeDouble(temperature);
        }
        if (apparentTemperature == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeDouble(apparentTemperature);
        }
        if (dewPoint == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeDouble(dewPoint);
        }
        if (humidity == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeDouble(humidity);
        }
        if (windSpeed == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeDouble(windSpeed);
        }
        if (windBearing == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(windBearing);
        }
        if (visibility == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeDouble(visibility);
        }
        if (cloudCover == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeDouble(cloudCover);
        }
        if (pressure == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeDouble(pressure);
        }
        if (ozone == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeDouble(ozone);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<CurrentlyResponse> CREATOR = new Parcelable.Creator<CurrentlyResponse>() {
        @Override
        public CurrentlyResponse createFromParcel(Parcel in) {
            return new CurrentlyResponse(in);
        }

        @Override
        public CurrentlyResponse[] newArray(int size) {
            return new CurrentlyResponse[size];
        }
    };
}