package com.example.movieapp;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Movie implements Parcelable {

    private String name;
    private int id;
    private List<Popular> populars = new ArrayList<>();

    public Movie(String nameGen, ArrayList<Popular> populars) {
        this.name = nameGen;
        this.populars = populars;
    }

    public  Movie(){

    }

    protected Movie(Parcel in) {
        name = in.readString();
        id = in.readInt();
        populars = in.createTypedArrayList(Popular.CREATOR);
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Popular> getPopulars() {
        return populars;
    }

    public void setPopulars(Popular populars) {
        this.populars.add(populars);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeInt(id);
        parcel.writeTypedList(populars);
    }
}