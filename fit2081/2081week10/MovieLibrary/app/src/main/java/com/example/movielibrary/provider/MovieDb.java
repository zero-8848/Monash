package com.example.movielibrary.provider;

import static com.example.movielibrary.provider.MovieDb.TABLE_NAME;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import io.reactivex.rxjava3.annotations.NonNull;

@Entity (tableName = TABLE_NAME)
public class MovieDb {
    public static final String TABLE_NAME ="movies";
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ";movie_id")
    @NonNull
    private int id;
    @ColumnInfo(name = "movie_title")
    private String title;
    @ColumnInfo(name = "movie_year")
    private int year;
    @ColumnInfo(name = "movie_country")
    private String country;
    @ColumnInfo(name = "movie_genre")
    private String genre;
    @ColumnInfo(name = "movie_cost")
    private int cost;
    @ColumnInfo(name = "movie_keywords")
    private String keywords;

    public MovieDb(String title, int year, String country, String genre, int cost, String keywords) {
        this.title = title;
        this.year = year;
        this.country = country;
        this.genre = genre;
        this.cost = cost;
        this.keywords = keywords;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public String getCountry() {
        return country;
    }

    public String getGenre() {
        return genre;
    }

    public int getCost() {
        return cost;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setId(int id) {
        this.id = id;
    }
}

