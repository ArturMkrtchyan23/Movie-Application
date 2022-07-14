package com.example.movieapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.movieapp.Popular;
import com.example.movieapp.R;

import java.util.ArrayList;
import java.util.List;

public class ResultsAdapter extends RecyclerView.Adapter<ResultsAdapter.ViewHolder> {
    private  Context context;
    private  List<Popular> populars;
    private OnItemClickListener listenerPopular;


    public ResultsAdapter(Context context, List<Popular> populars) {
        this.context = context;
        this.populars = populars;

    }

    public void setOnClickListener(OnItemClickListener listener) {
        this.listenerPopular = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.results_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Popular popular = populars.get(position);
        holder.textViewKino.setText(popular.getOriginal_title());

        holder.parentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listenerPopular != null){
                    listenerPopular.onClick(popular);
                }
            }
        });

        Glide.with(holder.imageViewKino.getContext())
                .load("https://image.tmdb.org/t/p/original/" + popular.getBackdrop_path())
                .into(holder.imageViewKino);
    }

    @Override
    public int getItemCount() {
        return populars.size();
    }

    public Filter getFilter(){
        return searchFilter;
    }

    private Filter searchFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<Popular> filteredList = new ArrayList<>();

            if (charSequence == null || charSequence.length() == 0){
                filteredList.addAll(populars);
            }else{
                String filterPattern = charSequence.toString().toLowerCase().trim();
                for (Popular item: populars){
                    if (item.getOriginal_title().toLowerCase().contains(filterPattern)){
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            populars.clear();
            populars.addAll((ArrayList) filterResults.values);
            notifyDataSetChanged();
        }
    };


    public class ViewHolder extends RecyclerView.ViewHolder {

        final ImageView imageViewKino;
        final TextView textViewKino;
        final View parentView;


        public ViewHolder(@NonNull View view) {
            super(view);

            parentView = view;
            imageViewKino = view.findViewById(R.id.imageKino);
            textViewKino = view.findViewById(R.id.nameKino);

        }
    }
    interface OnItemClickListener {
        void onClick(Popular popular);
    }
}
