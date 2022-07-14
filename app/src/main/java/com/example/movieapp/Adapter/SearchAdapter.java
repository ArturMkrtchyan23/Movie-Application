package com.example.movieapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.movieapp.Popular;
import com.example.movieapp.R;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    private Context context;
    private List<Popular> populars;

    public SearchAdapter(Context context, List<Popular> populars) {
        this.context = context;
        this.populars = populars;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Popular popular = populars.get(position);
        System.out.println("=-=--=" + popular.getOriginal_title());
        holder.textViewKinoSearch.setText(popular.getOriginal_title());
        Glide.with(holder.imageViewKinoSearch.getContext())
                .load("https://image.tmdb.org/t/p/original/" + popular.getBackdrop_path())
                .into(holder.imageViewKinoSearch);


    }

    public void clean(){
        populars.clear();

        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return populars.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView imageViewKinoSearch;
        final TextView textViewKinoSearch;
        final View parentView;

        public ViewHolder(@NonNull View view) {
            super(view);

            parentView = view;
            imageViewKinoSearch = view.findViewById(R.id.imageKinoSearch);
            textViewKinoSearch = view.findViewById(R.id.nameKinoSearch);
        }
    }
}
