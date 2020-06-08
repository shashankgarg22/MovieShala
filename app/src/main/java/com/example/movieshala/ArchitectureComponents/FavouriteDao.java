package com.example.movieshala.ArchitectureComponents;

import com.example.movieshala.objects.FavouriteDetails;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface FavouriteDao {
    @Insert
    void insert(FavouriteDetails favouriteDetails);

    @Delete
    void delete(FavouriteDetails favouriteDetails);

    @Query("SELECT * FROM favourite_table ORDER BY mid ASC")
    LiveData<List<FavouriteDetails>> getAllFavourite();
 }
