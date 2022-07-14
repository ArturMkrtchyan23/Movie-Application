package com.example.movieapp;

import android.os.Parcel;
import android.os.Parcelable;

public class MovieShowPage implements Parcelable{

    private String backdrop_path;
    private String original_title;
    private String overview;
    private String release_date;
    private int runtime;
    private float vote_average;
    private int vote_count;
    private int id;

    public MovieShowPage(String backdrop_path, String original_title, String overview, String release_date, int runtime, float vote_average, int vote_count, int id) {
        this.backdrop_path = backdrop_path;
        this.original_title = original_title;
        this.overview = overview;
        this.release_date = release_date;
        this.runtime = runtime;
        this.vote_average = vote_average;
        this.vote_count = vote_count;
        this.id = id;
    }

    protected MovieShowPage(Parcel in) {
        backdrop_path = in.readString();
        original_title = in.readString();
        overview = in.readString();
        release_date = in.readString();
        runtime = in.readInt();
        vote_average = in.readFloat();
        vote_count = in.readInt();
        id = in.readInt();
    }

    public static final Creator<MovieShowPage> CREATOR = new Creator<MovieShowPage>() {
        @Override
        public MovieShowPage createFromParcel(Parcel in) {
            return new MovieShowPage(in);
        }

        @Override
        public MovieShowPage[] newArray(int size) {
            return new MovieShowPage[size];
        }
    };

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public float getVote_average() {
        return vote_average;
    }

    public void setVote_average(float vote_average) {
        this.vote_average = vote_average;
    }

    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(backdrop_path);
        parcel.writeString(original_title);
        parcel.writeString(overview);
        parcel.writeString(release_date);
        parcel.writeInt(runtime);
        parcel.writeFloat(vote_average);
        parcel.writeInt(vote_count);
        parcel.writeInt(id);
    }
}
