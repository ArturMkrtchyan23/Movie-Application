package com.example.movieapp.DataBase;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface GenresDao {

    @Query("Select * from genresTable")
    List<GenresTable> getGenresList();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertGenres(GenresTable genresTable);
}
