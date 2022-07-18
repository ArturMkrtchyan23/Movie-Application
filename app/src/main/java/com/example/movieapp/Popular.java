package com.example.movieapp;


import android.os.Parcel;
import android.os.Parcelable;

public class Popular  implements Parcelable {

    private String original_title;
    private String backdrop_path;
    private int[] genre_ids;
    private int id;

    public Popular(String original_title, String backdrop_path, int[] genre_ids, int id) {
        this.original_title = original_title;
        this.backdrop_path = backdrop_path;
        this.genre_ids = genre_ids;
        this.id = id;
    }

    protected Popular(Parcel in) {
        original_title = in.readString();
        backdrop_path = in.readString();
        genre_ids = in.createIntArray();
        id = in.readInt();
    }

    public static final Creator<Popular> CREATOR = new Creator<Popular>() {
        @Override
        public Popular createFromParcel(Parcel in) {
            return new Popular(in);
        }

        @Override
        public Popular[] newArray(int size) {
            return new Popular[size];
        }
    };

    public Popular(){

    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public int[] getGenre_ids() {
        return genre_ids;
    }

    public void setGenre_ids(int[] genre_ids) {
        this.genre_ids = genre_ids;
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
        parcel.writeString(original_title);
        parcel.writeString(backdrop_path);
        parcel.writeIntArray(genre_ids);
        parcel.writeInt(id);
    }
}


