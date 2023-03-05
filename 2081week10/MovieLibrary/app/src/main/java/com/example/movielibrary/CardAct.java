package com.example.movielibrary;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CardAct extends AppCompatActivity {

    ArrayList<Movie> data = new ArrayList<>();


    //    Button btn;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    MyRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_layout);


        recyclerView = findViewById(R.id.my_recycler_view);

        layoutManager = new LinearLayoutManager(this);  //A RecyclerView.LayoutManager implementation which provides similar functionality to ListView.
        recyclerView.setLayoutManager(layoutManager);   // Also StaggeredGridLayoutManager and GridLayoutManager or a custom Layout manager


        adapter = new MyRecyclerAdapter(receiveMovie());
//        adapter.setData(data);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public String receiveMovie() {
//        return getIntent().getStringExtra("key");
        return (String)getIntent().getSerializableExtra("key");
    }
}