package com.example.movieapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;


import com.bumptech.glide.Glide;
import com.example.movieapp.Adapter.ResultsAdapter;
import com.example.movieapp.DataBase.MovieDatabase;
import com.example.movieapp.DataBase.MovieShowTable;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragmentMovieShow extends Fragment {

    ResultsAdapter resultsAdapter;
    private List<Popular> resultes;
    private  MovieShowPage movieShowPagess;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_movie_show, container, false);



        Bundle bundle = getArguments();
        Popular popular = bundle.getParcelable("popular");


        resultes = new ArrayList<>();
//        movieShowPagess = new ArrayList<>();


        int id  = popular.getId();

        resultsAdapter = new ResultsAdapter(getContext(), resultes);

        getMovieShow(id);


        return view;
    }

    public void getMovieShow(int id){

//        Map<String, String> parameters = new HashMap<>();
//        parameters.put("api_key", "9bf7eb7fc454535bd3e2c6330cafe135");

        Call<MovieShowPage> call = App.getTheMovieDbApi().getMovieShow(id);

        call.enqueue(new Callback<MovieShowPage>() {
            @Override
            public void onResponse(Call<MovieShowPage> call, Response<MovieShowPage> response) {
                if (!response.isSuccessful()){
                    return;
                }
                movieShowPagess = response.body();
                getMovieShowTable(movieShowPagess);

                ImageView img = getView().findViewById(R.id.imgKino_backdrop_path);
                Glide.with(img.getContext())
                        .load("https://image.tmdb.org/t/p/original/" + response.body().getBackdrop_path())
                        .into(img);

                TextView textNameKino = getView().findViewById(R.id.name_original_title);
                textNameKino.setText("Name: " + response.body().getOriginal_title());

                TextView textTime_runtime = getView().findViewById(R.id.time_runtime);
                textTime_runtime.setText(String.valueOf("Time: " +response.body().getRuntime() + " min"));

                TextView textData = getView().findViewById(R.id.movieData);
                textData.setText(String.valueOf("Release Date: " +response.body().getRelease_date()));

                TextView textCount = getView().findViewById(R.id.count);
                textCount.setText(String.valueOf("Views: " +response.body().getVote_count()));

                TextView textAverage = getView().findViewById(R.id.average);
                textAverage.setText(String.valueOf("Rating: " +response.body().getVote_average()));

                TextView textDescription = getView().findViewById(R.id.description_overview);
                textDescription.setText("Description: " + response.body().getOverview());


                Button button = getView().findViewById(R.id.playMovie);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        FragmentVideoView fragmentVideoView = new FragmentVideoView();

                        Bundle bundle = new Bundle();
                        bundle.putInt("res", response.body().getId());
                        fragmentVideoView.setArguments(bundle);


                        getActivity()
                                .getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.mainCounstruktor,  fragmentVideoView)
                                .addToBackStack(null)
                                .commit();
                    }
                });
            }

            @Override
            public void onFailure(Call<MovieShowPage> call, Throwable t) {
            }
        });
    }

    private void getMovieShowTable(MovieShowPage movieShowPages){
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                MovieDatabase movieDatabase = MovieDatabase.getInstance(getContext());

                    MovieShowTable movieShowTable = new MovieShowTable();
                    movieShowTable.setId(movieShowPages.getId());
                    movieShowTable.setBackdrop_path(movieShowPagess.getBackdrop_path());
                    movieShowTable.setOriginal_title(movieShowPages.getOriginal_title());
                    movieShowTable.setOverview(movieShowPages.getOverview());
                    movieShowTable.setRuntime(movieShowPages.getRuntime());
                    movieShowTable.setRelease_date(movieShowPages.getRelease_date());
                    movieShowTable.setVote_average(movieShowPages.getVote_average());
                    movieShowTable.setVote_count(movieShowPages.getVote_count());

                    movieDatabase.movieShowDao().insetMovieShowTable(movieShowTable);

                List<MovieShowTable> movieShowTableList = movieDatabase.movieShowDao().getMovieShowList();
                for (int i = 0; i < movieShowTableList.size(); i++){
                    System.out.println("0--=0-=0-=" + movieShowTableList.get(i).getId());
                }
                System.out.println("-09-09-09" + movieShowTableList);

            }
        });
    }

}