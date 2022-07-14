package com.example.movieapp.DataBase;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MovieDao {

    @Query("Select * from MovieTable")
    List<MovieTable> getMovieList();

//    @Query("SELECT * FROM movietable WHERE genre_ids IS :genre_ids")
//    List<MovieTable> getMovieForGenresIds(int[] genre_ids);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertMovie(MovieTable movieTable);
}
