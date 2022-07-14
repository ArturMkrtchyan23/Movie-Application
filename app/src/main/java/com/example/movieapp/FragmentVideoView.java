package com.example.movieapp;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragmentVideoView extends Fragment {

    private YouTubePlayerView youTubePlayerView;
    private List<ShowVideoMovie> resultsVideoShows;
    private  List<MovieShowPage> movieShowPages;

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_video_view, container, false);


        Bundle bundle = getArguments();
        int id = bundle.getInt("res", 0);


        resultsVideoShows = new ArrayList<>();
        movieShowPages = new ArrayList<>();

        getVideoShow(id);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

    @Override
    public void onPause() {
        super.onPause();
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
    }

    public void getVideoShow(int id){
//
//        Map<String, String> parameters = new HashMap<>();
//        parameters.put("api_key", "9bf7eb7fc454535bd3e2c6330cafe135");

        Call<ResultsVideoShow> call = App.getTheMovieDbApi().getVideoShow(id);

        call.enqueue(new Callback<ResultsVideoShow>() {
            @Override
            public void onResponse(Call<ResultsVideoShow> call, Response<ResultsVideoShow> response) {
                if (!response.isSuccessful()){
                    return;
                }


                youTubePlayerView = view.findViewById(R.id.youtubePlayerView);

                getLifecycle().addObserver(youTubePlayerView);
                youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                    @Override
                    public void onReady(@NonNull YouTubePlayer youTubePlayer) {

                        response.body();

                        response.body().resultsVideoShow.get(0).getKey();

                        String videoId = response.body().resultsVideoShow.get(0).getKey();
                        youTubePlayer.cueVideo(videoId, 0);
                    }
                });
            }

            @Override
            public void onFailure(Call<ResultsVideoShow> call, Throwable t) {

            }
        });
    }
}






