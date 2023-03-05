package com.example.movielibrary.provider;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MovieDao {
    @Query("select * from movies")
    LiveData<List<MovieDb>> getAllMovies();

    @Query("select * from movies where movie_title =:name")
    List<MovieDb> getMovie(String name);

    @Insert
    void addMovie(MovieDb movieDb);

    @Query("delete from movies where movie_title =:name")
    void deleteMovie(String name);

    @Query("delete FROM movies")
    void deleteAllMovies();

    @Query("delete  from movies where movie_year < 2020")
    void deleteMovieOlderThan2020();

    @Query("delete  from movies where movie_cost > 20")
    void deleteMovieCostAbove20();



}
