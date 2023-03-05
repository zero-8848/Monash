package com.example.movielibrary.provider;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class MovieRepository {

    private MovieDao mMovieDao;
    private LiveData<List<MovieDb>> allMovieGetted;
    MovieRepository(Application application){
        MovieDatabase db= MovieDatabase.getDatabase(application);
        mMovieDao= db.movieDao();
        allMovieGetted = mMovieDao.getAllMovies();
    }
    LiveData<List<MovieDb>> getAllMovies(){return allMovieGetted;}
    void insert(MovieDb movie){
        MovieDatabase.databaseWriteExecutor.execute(()->{
            mMovieDao.addMovie(movie);
        });
    }
    void deleteAll(){
        MovieDatabase.databaseWriteExecutor.execute(()->{
            mMovieDao.deleteAllMovies();
        });}
    void deleteMovieOlderThan2020(){
            MovieDatabase.databaseWriteExecutor.execute(()->mMovieDao.deleteMovieOlderThan2020());

        };

    void deleteMovieCostAbove20(){
            MovieDatabase.databaseWriteExecutor.execute(()->mMovieDao.deleteMovieCostAbove20());
        }
}


