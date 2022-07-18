package com.example.movieapp.DataBase;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "genresTable")
public class GenresTable {

    @PrimaryKey()
    private int genre_id;
    @ColumnInfo(name = "name")
    private String name;


    public GenresTable(int genre_id, String name) {
        this.genre_id = genre_id;
        this.name = name;
    }

    @Ignore
    public GenresTable(){

    }


    public int getGenre_id() {
        return genre_id;
    }

    public void setGenre_id(int genre_id) {
        this.genre_id = genre_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
