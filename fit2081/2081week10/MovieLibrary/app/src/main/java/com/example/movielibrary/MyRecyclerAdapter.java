package com.example.movielibrary;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder> {

    ArrayList<Movie> data;

    public MyRecyclerAdapter(String data) {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Movie>>(){}.getType();
        this.data = gson.fromJson(data, type);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false); //CardView inflated as RecyclerView list item
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.titleTv.setText(data.get(position).getTitle());
        holder.yearTv.setText(data.get(position).getYear());
        holder.countryTv.setText(data.get(position).getCountry());
        holder.genreTv.setText(data.get(position).getGenre());
        holder.costTv.setText(data.get(position).getCost());
        holder.keywordsTv.setText(data.get(position).getKeywords());
        holder.actornumberTv.setText(data.get(position).getActNumber());
        holder.usdTv.setText(data.get(position).getUsd());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTv;
        public TextView yearTv;
        public TextView countryTv;
        public TextView genreTv;
        public TextView costTv;
        public TextView keywordsTv;
        public TextView actornumberTv;
        public TextView usdTv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTv = itemView.findViewById(R.id.title_id);
            yearTv = itemView.findViewById(R.id.year_id);
            countryTv = itemView.findViewById(R.id.country_id);
            genreTv = itemView.findViewById(R.id.genre_id);
            costTv = itemView.findViewById(R.id.cost_id);
            keywordsTv=itemView.findViewById(R.id.keywords_id);
            actornumberTv = itemView.findViewById(R.id.actornumber_id);
            usdTv = itemView.findViewById(R.id.usd_id);

        }
    }
}