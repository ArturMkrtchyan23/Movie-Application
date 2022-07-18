package com.example.movieapp.DataBase;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MovieShowDao {

    @Query("Select * from movieShowTable WHERE id= :id")
    MovieShowTable getMovieShowList(int id);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insetMovieShowTable(MovieShowTable movieShowTable);

}
