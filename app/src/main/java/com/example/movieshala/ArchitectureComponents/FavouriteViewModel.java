package com.example.movieshala.ArchitectureComponents;

import android.app.Application;

import com.example.movieshala.objects.FavouriteDetails;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class FavouriteViewModel extends AndroidViewModel {

    private FavouriteRepository mFavouriteRepository;
    private LiveData<List<FavouriteDetails>> mAllFavourites;
    public FavouriteViewModel(@NonNull Application application) {
        super(application);
        mFavouriteRepository=new FavouriteRepository(application);
        mAllFavourites =mFavouriteRepository.getAllFavourites();
    }
    public void insert(FavouriteDetails favouriteDetails) {
        mFavouriteRepository.insert(favouriteDetails);
    }

    public void delete(FavouriteDetails favouriteDetails) {
        mFavouriteRepository.delete(favouriteDetails);
    }


    public LiveData<List<FavouriteDetails>> getAllFavourites() {
        return mAllFavourites;
    }
}
