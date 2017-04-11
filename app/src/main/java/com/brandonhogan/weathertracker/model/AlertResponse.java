package com.brandonhogan.weathertracker.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


@SuppressWarnings("unused, weakerAccess")
public class AlertResponse implements Parcelable {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("time")
    @Expose
    private Integer time;
    @SerializedName("expires")
    @Expose
    private Integer expires;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("uri")
    @Expose
    private String uri;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Integer getExpires() {
        return expires;
    }

    public void setExpires(Integer expires) {
        this.expires = expires;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    protected AlertResponse(Parcel in) {
        title = in.readString();
        time = in.readByte() == 0x00 ? null : in.readInt();
        expires = in.readByte() == 0x00 ? null : in.readInt();
        description = in.readString();
        uri = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        if (time == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(time);
        }
        if (expires == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(expires);
        }
        dest.writeString(description);
        dest.writeString(uri);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<AlertResponse> CREATOR = new Parcelable.Creator<AlertResponse>() {
        @Override
        public AlertResponse createFromParcel(Parcel in) {
            return new AlertResponse(in);
        }

        @Override
        public AlertResponse[] newArray(int size) {
            return new AlertResponse[size];
        }
    };
}