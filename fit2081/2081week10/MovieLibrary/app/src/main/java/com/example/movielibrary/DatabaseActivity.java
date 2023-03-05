package com.example.movielibrary;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movielibrary.provider.DatabaseAdapter;
import com.example.movielibrary.provider.MovieViewModel;

public class DatabaseActivity extends AppCompatActivity {
    TextView dataAmountTv;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.database_recycler_layout);
        showDatabase();
    }

    public void showDatabase(){
        dataAmountTv = findViewById(R.id.dataCount);
        RecyclerView recyclerView = findViewById(R.id.database_recycler_layout_id);
        DatabaseAdapter adapter = new DatabaseAdapter();

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        MovieViewModel movieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);
        movieViewModel.getAllMovies().observe(this, newData -> {
            adapter.setMovies(newData);
            adapter.notifyDataSetChanged();
            dataAmountTv.setText(newData.size() + "");
        });
    }
}
