package com.example.movieapp.DataBase;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MovieShowDao {

    @Query("Select * from movieShowTable")
    List<MovieShowTable> getMovieShowList();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insetMovieShowTable(MovieShowTable movieShowTable);

}
