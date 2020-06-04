package com.example.movieshala;

class MovieDetail {

    private String mMovieTitle;
    private Double mMovieRatings;
    private String mMovieImage;
    private String mMovieDate;
    private String mMovieSynopsis;

    public MovieDetail(String movieTitle, String movieDate, Double movieRatings, String movieSynopsis, String movieImage){
        mMovieDate=movieDate;
        mMovieImage=movieImage;
        mMovieRatings=movieRatings;
        mMovieSynopsis=movieSynopsis;
        mMovieTitle=movieTitle;
    }

    public String getMovieTitle(){return mMovieTitle;}
    public Double getMovieRatings(){return mMovieRatings;}
    public String getMovieImage(){return mMovieImage;}
    public String getMovieDate(){return mMovieDate;}
    public String getMovieSynopsis(){return mMovieSynopsis;}

}
