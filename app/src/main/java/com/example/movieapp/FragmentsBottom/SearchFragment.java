package com.example.movieapp.FragmentsBottom;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapp.Adapter.SearchAdapter;
import com.example.movieapp.App;
import com.example.movieapp.Popular;
import com.example.movieapp.R;
import com.example.movieapp.Results;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SearchFragment extends Fragment {
    private SearchView searchView;
    private SearchAdapter searchAdapter;
    private RecyclerView recyclerSearch;
    private List<Popular> populars;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_search, container, false);

        searchView = view.findViewById(R.id.searchView);

        populars = new ArrayList<>();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                if (s.isEmpty()){
                    searchAdapter.clean();
                }else {
                    getSearchMovie(s);
                }


                return false;
            }
        });

        return view;
    }
    public void getSearchMovie(String s){

        Map<String, String> parameters = new HashMap<>();
//        parameters.put("api_key", "9bf7eb7fc454535bd3e2c6330cafe135");
        parameters.put("query", s);

        Call<Results> call = App.getTheMovieDbApi().getSearchMovie(parameters);

        call.enqueue(new Callback<Results>() {
            @Override
            public void onResponse(Call<Results> call, Response<Results> response) {
                if (!response.isSuccessful()){
                    return;
                }
                Results results = response.body();
                System.out.println("=-=-=--=" + results);

                recyclerSearch = getView().findViewById(R.id.searchRecycler);

                searchAdapter = new SearchAdapter(getContext(), results.getResults());
                recyclerSearch.setLayoutManager(new GridLayoutManager(getContext(),2));
                recyclerSearch.setAdapter(searchAdapter);


                searchAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Results> call, Throwable t) {

            }
        });
    }
}