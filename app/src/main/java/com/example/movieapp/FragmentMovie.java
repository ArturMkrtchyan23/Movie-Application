package com.example.movieapp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.movieapp.Adapter.GenresAdapter;
import com.example.movieapp.DataBase.GenresTable;
import com.example.movieapp.DataBase.MovieDatabase;
import com.example.movieapp.DataBase.MovieTable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragmentMovie extends Fragment {

    private GenresAdapter adapterGenres;
    private List<Popular> resultes;
    private List<Movie> generes;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_movie, container, false);

        if (isConnected()){
            System.out.println("-=-==-=-" + isConnected());
            getMovie();
            getPopular();
        }else {
            getGeneresFromDB();
            getResultsFromDB();
        }

        return view;
    }

    private void getMovie(){

//        Map<String, String> parameters = new HashMap<>();
//        parameters.put("api_key", "9bf7eb7fc454535bd3e2c6330cafe135");

        Call<Genres> call = App.getTheMovieDbApi().getGenres();

        call.enqueue(new Callback<Genres>() {
            @Override
            public void onResponse(Call<Genres> call, Response<Genres> response) {
                if (!response.isSuccessful()){
                    return;
                }

                generes = response.body().genres;

                getGenresTable(generes);

                RecyclerView recyclerViewGenres = getView().findViewById(R.id.recyclerViewGenres);

                recyclerViewGenres.setHasFixedSize(true);

                adapterGenres = new GenresAdapter(getContext(), generes);
                recyclerViewGenres.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false));
                recyclerViewGenres.setAdapter(adapterGenres);
            }

            @Override
            public void onFailure(Call<Genres> call, Throwable t) {
            }
        });
    }


    public void getPopular(){
//        Map<String, String> parameters = new HashMap<>();
//        parameters.put("api_key", "9bf7eb7fc454535bd3e2c6330cafe135");

        Call<Results> call = App.getTheMovieDbApi().getPopular();

        call.enqueue(new Callback<Results>() {
            @Override
            public void onResponse(Call<Results> call, Response<Results> response) {
                if (!response.isSuccessful()){
                    return;
                }

                Results results = response.body();
                resultes = results.getResults();
                getMovieTable(resultes);
                for (Movie gener : generes) {
                    int id = gener.getId();
                    for (Popular popular : results.getResults()) {
                        for (int i =0; i < popular.getGenre_ids().length; i++) {
                            if (popular.getGenre_ids()[i] == id) {
                                gener.setPopulars(popular);
                                break;
                            }
                        }
                    }
                }

                adapterGenres.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Results> call, Throwable t) {
            }
        });
    }

    private void getGeneresFromDB() {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                generes = new ArrayList<>();
                MovieDatabase movieDatabase = MovieDatabase.getInstance(getContext());
                List<GenresTable> genresList = movieDatabase.genresDao().getGenresList();
                    for (int i = 0; i < genresList.size(); i++){
                        Movie movie = new Movie();
                        movie.setName(genresList.get(i).getName());
                        movie.setId(genresList.get(i).getGenre_id());
                        System.out.println("-=-=-=-=" + movie.getName());
                        generes.add(movie);
                    }
            }
        });
    }

    private void getResultsFromDB(){
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                List<Popular> results = new ArrayList<>();
                MovieDatabase movieDatabase = MovieDatabase.getInstance(getContext());
                List<MovieTable> movieList = movieDatabase.movieDao().getMovieList();
                for (int i = 0; i < movieList.size(); i++){
                    Popular popular = new Popular();
                    popular.setBackdrop_path(movieList.get(i).getBackdrop_path());
                    popular.setId(movieList.get(i).getId());
                    popular.setOriginal_title(movieList.get(i).getOriginal_title());
                    popular.setGenre_ids(movieList.get(i).getGenre_ids());
                    System.out.println("-==00-0==" + popular.getGenre_ids());
                    results.add(popular);

                }
                for (Movie gener : generes) {
                    int id = gener.getId();
                    for (Popular popular : results) {
                        for (int i =0; i < popular.getGenre_ids().length; i++) {
                            if (popular.getGenre_ids()[i] == id) {
                                gener.setPopulars(popular);
                                break;
                            }
                        }
                    }
                }
                RecyclerView recyclerViewGenres = getView().findViewById(R.id.recyclerViewGenres);

                recyclerViewGenres.setHasFixedSize(true);

                adapterGenres = new GenresAdapter(getContext(), generes);
                recyclerViewGenres.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false));
                recyclerViewGenres.setAdapter(adapterGenres);

            }
        });

    }




    private void getGenresTable(List<Movie> genres){
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                MovieDatabase movieDatabase = MovieDatabase.getInstance(getContext());
                for (int i = 0; i < genres.size(); i++){
                    GenresTable genresTable = new GenresTable();
                    genresTable.setName(genres.get(i).getName());
                    genresTable.setGenre_id(genres.get(i).getId());

                    movieDatabase.genresDao().insertGenres(genresTable);
                }
                List<GenresTable> genresList =  movieDatabase.genresDao().getGenresList();
                for (int i = 0; i < genresList.size(); i++){
                    System.out.println("-=-=-=" + genresList.get(i).getName());
                }
                System.out.println("=-=-=-=-" + genresList);
            }
        });
    }
    private void getMovieTable(List<Popular> results){
            AsyncTask.execute(new Runnable() {
                @Override
                public void run() {
                    MovieDatabase movieDatabase = MovieDatabase.getInstance(getContext());
                    for (int i = 0; i < results.size(); i++){
                        MovieTable movieTable = new MovieTable();

                        movieTable.setOriginal_title(results.get(i).getOriginal_title());
                        movieTable.setBackdrop_path(results.get(i).getBackdrop_path());
                        movieTable.setGenre_ids(results.get(i).getGenre_ids());
                        movieTable.setId(results.get(i).getId());

                        movieDatabase.movieDao().insertMovie(movieTable);
                    }
                    List<MovieTable> movieList = movieDatabase.movieDao().getMovieList();
                    for (int i = 0; i < movieList.size(); i++){
                        System.out.println("==-=000-=-" + Arrays.toString(movieList.get(i).getGenre_ids()));
                    }
                    System.out.println("0-0-0-0-=-" + movieList);

                }
            });
    }

    boolean isConnected(){
        ConnectivityManager connectivityManager = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo wifiConn = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobileConn = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if (wifiConn != null && wifiConn.isConnected() || (mobileConn != null && mobileConn.isConnected())){
            return true;
        }else {
            return false;
        }

    }
}