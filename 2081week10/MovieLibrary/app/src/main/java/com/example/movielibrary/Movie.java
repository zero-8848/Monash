package com.example.movielibrary;

public class Movie {

    private String title;
    private String year;
    private String country;
    private String genre;
    private String cost;
    private String keywords;
    private String actNumber;
    private String usd;

    public Movie(String title, String year, String country, String genre, String cost, String keywords, String actNumber,String usd) {
        this.title = title;
        this.year = year;
        this.country = country;
        this.genre = genre;
        this.cost = cost;
        this.keywords = keywords;
        this.actNumber = actNumber;
        this.usd=usd;
    }

    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

    public String getCost() {
        return cost;
    }

    public String getActNumber() {
        return actNumber;
    }

    public String getCountry() {
        return country;
    }

    public String getGenre() {
        return genre;
    }

    public String getKeywords() {
        return keywords;
    }

    public String getUsd() {
        return usd;
    }
}