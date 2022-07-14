package com.example.movieapp;


import android.os.Parcel;
import android.os.Parcelable;

public class ShowVideoMovie implements Parcelable {
    private String key;

    public ShowVideoMovie(String key) {
        this.key = key;
    }

    protected ShowVideoMovie(Parcel in) {
        key = in.readString();
    }

    public static final Creator<ShowVideoMovie> CREATOR = new Creator<ShowVideoMovie>() {
        @Override
        public ShowVideoMovie createFromParcel(Parcel in) {
            return new ShowVideoMovie(in);
        }

        @Override
        public ShowVideoMovie[] newArray(int size) {
            return new ShowVideoMovie[size];
        }
    };

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(key);
    }
}
