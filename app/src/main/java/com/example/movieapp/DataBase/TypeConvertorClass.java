package com.example.movieapp.DataBase;

import androidx.room.TypeConverter;

import com.google.gson.Gson;

public class TypeConvertorClass {
    @TypeConverter
    public static Gson fromGson(int[] genre_ids) {
        return genre_ids == null ? null : new Gson();
    }
}
