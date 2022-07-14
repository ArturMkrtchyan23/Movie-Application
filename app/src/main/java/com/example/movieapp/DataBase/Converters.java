package com.example.movieapp.DataBase;

import androidx.room.TypeConverter;

import org.json.JSONArray;
import org.json.JSONException;



public class Converters{

    @TypeConverter
    public static int[] fromString(String value) {
       try {
           JSONArray jsonArray  = new JSONArray(value);
           int[] intArray = new int[jsonArray.length()];
           for (int i = 0; i < jsonArray.length(); i++){
               intArray[i] = Integer.parseInt(jsonArray.getString(i));
           }
           return intArray;

       } catch (JSONException e) {
           e.printStackTrace();
       }
       return null;
    }
    @TypeConverter
    public static String fromInts(int[] values){
        JSONArray jsonArray = new JSONArray();
        for (int value : values) {
            jsonArray.put(value);
        }
        return jsonArray.toString();
    }

}
