package com.example.movieshala.ArchitectureComponents;

import android.content.Context;
import android.os.AsyncTask;

import com.example.movieshala.objects.FavouriteDetails;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;


@Database(entities = FavouriteDetails.class,version = 1,exportSchema = false)
abstract class FavouriteDatabases  extends RoomDatabase {

    public static FavouriteDatabases instance;
    public abstract FavouriteDao favouriteDao();

    public static synchronized FavouriteDatabases getInstance(Context context){
        if(instance==null){
            instance= Room.databaseBuilder(context.getApplicationContext()
                    ,FavouriteDatabases.class
                    ,"favourite_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
    public static RoomDatabase.Callback PopulateDb = new RoomDatabase.Callback() {

        //Called when database is created for the first time
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            new PopulateDbAsyncTask(instance).execute();
            super.onCreate(db);
        }
    };


    public static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private FavouriteDao favouriteDao;

        public PopulateDbAsyncTask(FavouriteDatabases db) {
            this.favouriteDao = db.favouriteDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            favouriteDao.insert(new FavouriteDetails(1,"Title 1", "Discription 1", "2019-12-07", 5.60));
            favouriteDao.insert(new FavouriteDetails(2,"Title 2", "Discription 2", "2019-12-08", 6.66));
            favouriteDao.insert(new FavouriteDetails(3,"Title 3", "Discription 3", "2019-12-09", 7.67));

            return null;
        }
    }
}
