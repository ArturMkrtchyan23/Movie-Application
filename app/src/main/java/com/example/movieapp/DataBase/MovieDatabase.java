package com.example.movieapp.DataBase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {GenresTable.class, MovieTable.class, MovieShowTable.class},  exportSchema = false, version = 2)
@TypeConverters({Converters.class})
public abstract class MovieDatabase extends RoomDatabase {

    private static final String DB_NAME = "genres";
    private static MovieDatabase instance;

    public static synchronized MovieDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),MovieDatabase.class, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

    public abstract GenresDao genresDao();
    public abstract MovieDao movieDao();
    public abstract MovieShowDao movieShowDao();

}
