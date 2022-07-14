package com.example.movieapp.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapp.FragmentMovieShow;
import com.example.movieapp.Movie;
import com.example.movieapp.Popular;
import com.example.movieapp.R;

import java.util.List;

public class GenresAdapter extends RecyclerView.Adapter<GenresAdapter.ViewHolder> {

    private final Context context;
    private final List<Movie> movies;
    private final LayoutInflater inflater;
    private ResultsAdapter resultsAdapter;


    public GenresAdapter(Context context,List<Movie> movies)  {
        this.context = context;
        this.movies = movies;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.genres_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Movie movie = movies.get(position);

        List singleMovieItems = movies.get(position).getPopulars();
        holder.textViewGenres.setText(movie.getName());



        resultsAdapter = new ResultsAdapter(context, singleMovieItems);

        resultsAdapter.setOnClickListener(new ResultsAdapter.OnItemClickListener() {
            @Override
            public void onClick(Popular popular) {

                FragmentMovieShow fragmentMovieShow = new FragmentMovieShow();

                Bundle bundle = new Bundle();
                bundle.putParcelable("popular", popular);
                fragmentMovieShow.setArguments(bundle);

                ((AppCompatActivity)context).getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.mainCounstruktor, fragmentMovieShow)
                        .addToBackStack(null)
                        .commit();
            }
        });


        holder.recyclerViewGen.setHasFixedSize(true);
        holder.recyclerViewGen.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        holder.recyclerViewGen.setAdapter(resultsAdapter);
    }



    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        final TextView textViewGenres;
        final RecyclerView recyclerViewGen;

        public ViewHolder(@NonNull View view) {
            super(view);

            textViewGenres = view.findViewById(R.id.textGenres);
            recyclerViewGen = view.findViewById(R.id.recyclerViewGen);
        }
    }
}
