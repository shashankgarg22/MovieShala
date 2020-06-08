package com.example.movieshala.objects;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName ="favourite_table")
public class FavouriteDetails {

    @PrimaryKey
    private int mId;
    private String mTitle;
    private String mDescription;
    private String mDate;
    private double mRating;

    public FavouriteDetails(int id,String title, String description, String date, double rating) {
        mTitle = title;
        mDescription = description;
        mDate = date;
        mRating = rating;
        mId=id;
    }


    public int getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getDate() {
        return mDate;
    }

    public double getRating() {
        return mRating;
    }
}
