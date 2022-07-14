package com.example.movieapp;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface TheMovieDbApi {

    @GET("genre/movie/list")
    Call<Genres> getGenres();

    @GET("movie/popular")
    Call<Results> getPopular();

    @GET("movie/{id}")
    Call<MovieShowPage> getMovieShow(@Path("id") int popularId);

    @GET("movie/{id}/videos")
    Call<ResultsVideoShow> getVideoShow(@Path("id") int popularId);

    @GET("search/movie")
    Call<Results> getSearchMovie(@QueryMap Map<String, String> parameters);


}
