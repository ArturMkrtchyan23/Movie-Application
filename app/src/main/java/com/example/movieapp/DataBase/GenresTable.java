package com.example.movieapp.DataBase;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "genresTable")
public class GenresTable {

    @PrimaryKey()
    private int genre_ids;
    @ColumnInfo(name = "name")
    private String name;


    public GenresTable(int genre_ids, String name) {
        this.genre_ids = genre_ids;
        this.name = name;
    }

    @Ignore
    public GenresTable(){

    }


    public int getGenre_ids() {
        return genre_ids;
    }

    public void setGenre_ids(int genre_ids) {
        this.genre_ids = genre_ids;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
