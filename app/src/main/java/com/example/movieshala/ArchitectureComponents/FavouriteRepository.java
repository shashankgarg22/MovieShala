package com.example.movieshala.ArchitectureComponents;

import android.app.Application;
import android.os.AsyncTask;

import com.example.movieshala.objects.FavouriteDetails;

import java.util.List;

import androidx.lifecycle.LiveData;

public class FavouriteRepository {
    private FavouriteDao mFavouriteDao;
    private LiveData<List<FavouriteDetails>> mAllFavourites;

    public FavouriteRepository(Application application) {
        FavouriteDatabases favouriteDatabase = FavouriteDatabases.getInstance(application);
        mFavouriteDao = favouriteDatabase.favouriteDao();
        mAllFavourites = mFavouriteDao.getAllFavourite();
    }

    public void insert(FavouriteDetails favouriteDetails) {
        new insertAsyncTask(mFavouriteDao).execute(favouriteDetails);
    }

    public void delete(FavouriteDetails favouriteDetails) {
        new deleteAsyncTask(mFavouriteDao).execute(favouriteDetails);
    }

    public LiveData<List<FavouriteDetails>> getAllFavourites() {
        return mAllFavourites;
    }



    private static class insertAsyncTask extends AsyncTask<FavouriteDetails, Void, Void> {
        FavouriteDao cFavouriteDao;

        public insertAsyncTask(FavouriteDao favouriteDao) {
            cFavouriteDao = favouriteDao;
        }

        @Override
        protected Void doInBackground(FavouriteDetails... favouriteDetails) {
            cFavouriteDao.insert(favouriteDetails[0]);
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<FavouriteDetails, Void, Void> {
        FavouriteDao cFavouriteDao;

        public deleteAsyncTask(FavouriteDao favouriteDao) {
            cFavouriteDao = favouriteDao;
        }

        @Override
        protected Void doInBackground(FavouriteDetails... favouriteDetails) {
            cFavouriteDao.delete(favouriteDetails[0]);
            return null;
        }
    }

}
