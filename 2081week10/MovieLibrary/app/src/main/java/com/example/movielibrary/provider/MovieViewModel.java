package com.example.movielibrary.provider;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.movielibrary.Movie;

import java.util.List;

public class MovieViewModel extends AndroidViewModel {
    private MovieRepository mRepository;
    private LiveData<List<MovieDb>> mAllMovies;
    public MovieViewModel(@NonNull Application application) {
        super(application);
        mRepository = new MovieRepository(application);
        mAllMovies = mRepository.getAllMovies();
    }
    public LiveData<List<MovieDb>> getAllMovies() {
        return mAllMovies;
    }

    public void insert(MovieDb movieDb) {
        mRepository.insert(movieDb);
    }
    public void deleteAll(){
        mRepository.deleteAll();
    }

    public void deleteMovieOlderThan2020(){
        mRepository.deleteMovieOlderThan2020();
    }

    public void deleteMovieCostAbove20(){
        mRepository.deleteMovieCostAbove20();
    }
}
