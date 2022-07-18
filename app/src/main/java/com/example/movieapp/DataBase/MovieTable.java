package com.example.movieapp.DataBase;



import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "movieTable")
public class MovieTable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "original_title")
    private String original_title;
    @ColumnInfo(name = "backdrop_path")
    private String backdrop_path;
    @ColumnInfo(name = "genre_ids")
    private int[] genre_ids;


    public MovieTable(int id, String original_title, String backdrop_path, int[] genre_ids) {
        this.id = id;
        this.original_title = original_title;
        this.backdrop_path = backdrop_path;
        this.genre_ids = genre_ids;
    }

    @Ignore
    public MovieTable(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
