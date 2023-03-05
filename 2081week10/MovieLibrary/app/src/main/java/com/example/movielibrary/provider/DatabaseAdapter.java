package com.example.movielibrary.provider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movielibrary.Movie;
import com.example.movielibrary.MyRecyclerAdapter;
import com.example.movielibrary.R;

import java.util.ArrayList;
import java.util.List;

public class DatabaseAdapter extends RecyclerView.Adapter<DatabaseAdapter.DatabaseViewHolder> {
    @NonNull
    @Override
    public DatabaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.database_item_layout, parent, false); //CardView inflated as RecyclerView list item
        DatabaseViewHolder viewHolder = new DatabaseViewHolder(v);
        return viewHolder;
    }

    List<MovieDb> data;

    public DatabaseAdapter() {


    }

    public void setMovies(List<MovieDb> newMovieDb) {
        data = newMovieDb;
    }

    @Override
    public void onBindViewHolder(@NonNull DatabaseViewHolder holder, int position) {
        holder.titleTv.setText(data.get(position).getTitle());
        holder.yearTv.setText(String.valueOf((data.get(position).getYear())));
        holder.countryTv.setText(data.get(position).getCountry());
        holder.genreTv.setText(data.get(position).getGenre());
        holder.costTv.setText(String.valueOf(data.get(position).getCost()));
        holder.keywordsTv.setText(data.get(position).getKeywords());

    }

    @Override
    public int getItemCount() {
        if (data == null) {
            return 0;
        } else {
            return data.size();
        }
    }

    public class DatabaseViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTv;
        public TextView yearTv;
        public TextView countryTv;
        public TextView genreTv;
        public TextView costTv;
        public TextView keywordsTv;


        public DatabaseViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTv = itemView.findViewById(R.id.movie_name);
            yearTv = itemView.findViewById(R.id.movie_year);
            countryTv = itemView.findViewById(R.id.movie_country);
            genreTv = itemView.findViewById(R.id.movie_genre);
            costTv = itemView.findViewById(R.id.movie_cost);
            keywordsTv = itemView.findViewById(R.id.movie_keywords);

        }
    }
}
