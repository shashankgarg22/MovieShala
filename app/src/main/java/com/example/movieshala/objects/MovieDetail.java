package com.example.movieshala.objects;

public class MovieDetail {

    private String mMovieTitle;
    private Double mMovieRatings;
    private String mMovieImage;
    private String mMovieDate;
    private String mMovieSynopsis;
    private String mMovieId;
    private String backimage;

    public MovieDetail(String movieTitle, String movieDate, Double movieRatings, String movieSynopsis, String movieImage, String movieId, String backimage) {
        mMovieDate = movieDate;
        mMovieImage = movieImage;
        mMovieRatings = movieRatings;
        mMovieSynopsis = movieSynopsis;
        mMovieTitle = movieTitle;
        mMovieId = movieId;

        this.backimage = backimage;
    }

    public String getMovieTitle() {
        return mMovieTitle;
    }

    public Double getMovieRatings() {
        return mMovieRatings;
    }

    public String getMovieImage() {
        return mMovieImage;
    }

    public String getMovieDate() {
        return mMovieDate;
    }

    public String getMovieSynopsis() {
        return mMovieSynopsis;
    }

    public String getMovieId() {
        return mMovieId;
    }

    public String getBackimage() {
        return backimage;
    }
}
