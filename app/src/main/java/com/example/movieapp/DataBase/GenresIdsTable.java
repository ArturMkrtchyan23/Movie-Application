package com.example.movieapp.DataBase;

import androidx.room.ColumnInfo;


public class GenresIdsTable {

    @ColumnInfo(name = "genre_ids")
    private int[] genre_ids;
}
