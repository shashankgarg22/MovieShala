package com.example.movieshala;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.movieshala.Adapters.ReviewsAdapter;
import com.example.movieshala.Adapters.TrailerAdapter;
import com.example.movieshala.ArchitectureComponents.FavouriteViewModel;
import com.example.movieshala.Utility.MovieUtils;
import com.example.movieshala.objects.FavouriteDetails;
import com.example.movieshala.objects.Reviews;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Movie_Detail_Activity extends AppCompatActivity {
    String mid;
    String title;
    String overView;
    String date;
    double rating;
    RecyclerView videoRecyclerView;
    TrailerAdapter movieVideoAdapter;
    RecyclerView reviewRecyclerView;
    ReviewsAdapter movieReviewAdapter;
    ImageView reviewLoadImage;
    ImageView videoLoadImage;
    FavouriteViewModel favouritesViewModel;
    FavouriteDetails favouriteDetails;
    int flag = 0;
    List<FavouriteDetails> allFavouriteList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie__detail_);
        Intent intent = getIntent();
        updateDetails(intent);
        updateVideo();
        updateReviews();

        reviewLoadImage = findViewById(R.id.review_load_image);
        videoLoadImage = findViewById(R.id.trailer_load_image);
        reviewLoadImage.setVisibility(View.VISIBLE);
        videoLoadImage.setVisibility(View.VISIBLE);

        Picasso.get().load(R.drawable.load5).into(reviewLoadImage);
        Picasso.get().load(R.drawable.load5).into(videoLoadImage);

        trailerAsynctask trailerAsynctask = new trailerAsynctask();
        trailerAsynctask.execute(getVideoApiLink(mid));

        MovieReviewAsyncTask asyncTaskReview = new MovieReviewAsyncTask();
        asyncTaskReview.execute(getReviewApiLink(mid));

        FloatingActionButton favButton = findViewById(R.id.set_favourite);
        favButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (int i = 0; i < allFavouriteList.size(); i++) {
                    if (allFavouriteList.get(i).getId() == Integer.parseInt(mid)) {
                        flag = 1;
                    }
                }

                if (flag == 0) {

                    addToFavourite();
                    flag = 1;
                } else {
                    flag = 0;
                    deleteFromFavourite();
                }

            }
        });

        favouritesViewModel = ViewModelProviders.of(this).get(FavouriteViewModel.class);
        favouritesViewModel.getAllFavourites().observe(this, new Observer<List<FavouriteDetails>>() {
            @Override
            public void onChanged(List<FavouriteDetails> favouriteDetails) {
                allFavouriteList = favouriteDetails;


            }
        });


    }
    private void deleteFromFavourite() {
        favouriteDetails = new FavouriteDetails(Integer.parseInt(mid), title, overView, date, rating);
        favouritesViewModel.delete(favouriteDetails);
        Toast.makeText(Movie_Detail_Activity.this, "Deleted from favourite", Toast.LENGTH_SHORT).show();

    }

    private void addToFavourite() {


        favouriteDetails = new FavouriteDetails(Integer.parseInt(mid), title, overView, date, rating);
        favouritesViewModel.insert(favouriteDetails);
        Toast.makeText(Movie_Detail_Activity.this, "Added to favourite", Toast.LENGTH_SHORT).show();

    }
    private void updateVideo() {
        ArrayList<String> arrayList = new ArrayList<>();
        videoRecyclerView = findViewById(R.id.trailer_recycler_view);
        videoRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        movieVideoAdapter = new TrailerAdapter(arrayList, this);
        videoRecyclerView.setAdapter(movieVideoAdapter);
    }

    private void updateReviews() {
        ArrayList<Reviews> movieReviewArrayList = new ArrayList<>();
        reviewRecyclerView = findViewById(R.id.reviews_recycler_view);
        reviewRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        movieReviewAdapter = new ReviewsAdapter(Movie_Detail_Activity.this, movieReviewArrayList);
        reviewRecyclerView.setAdapter(movieVideoAdapter);

    }

    @SuppressLint("SetTextI18n")
    private void updateDetails(Intent intent) {
        title = intent.getStringExtra("title");
        overView = intent.getStringExtra("overview");
        date = intent.getStringExtra("date");
        Bundle b = getIntent().getExtras();
        rating = b.getDouble("key");
        String image = intent.getStringExtra("image");
        String id = intent.getStringExtra("id");
        mid = id;

        Log.i("MovieDetail-output", title);
        Log.i("MovieDetail-output", overView);
        Log.i("MovieDetail-output", date);
        Log.i("MovieDetail-output", String.valueOf(rating));
        Log.i("MovieDetail-output", image);
        Log.i("MovieDetail-output", id);

        TextView titleTextView = findViewById(R.id.title);
        TextView dateTextView = findViewById(R.id.date);
        TextView ratingTextView = findViewById(R.id.rating);
        TextView overViewTextView = findViewById(R.id.overView);
        ImageView imageView = findViewById(R.id.image);
        titleTextView.setText(title);
        dateTextView.setText(getString(R.string.release) + date);
        ratingTextView.setText(getString(R.string.rate) + rating + getString(R.string.out));
        overViewTextView.setText(overView);

        Picasso.get().load(image).placeholder(R.drawable.load5).into(imageView);
    }

    //Creates VideoApi link from id
    private String getVideoApiLink(String id) {
        String videoLink = "https://api.themoviedb.org/3/movie/" + id + "/videos?api_key=8f067240d8717f510b4c79abe9f714b7&language=en-US";
        return videoLink;
    }

    //Creates ReviewApi link from id
    private String getReviewApiLink(String id) {
        String reviewLink = "https://api.themoviedb.org/3/movie/" + id + "/reviews?api_key=8f067240d8717f510b4c79abe9f714b7&language=en-US";
        return reviewLink;
    }

    public class trailerAsynctask extends AsyncTask<String, Void, ArrayList<String>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if (videoRecyclerView.getVisibility() == View.VISIBLE) {
                videoRecyclerView.setVisibility(View.GONE);
            }
            videoLoadImage.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(ArrayList<String> strings) {
            super.onPostExecute(strings);
            movieVideoAdapter = new TrailerAdapter(strings, Movie_Detail_Activity.this);
            videoRecyclerView.setAdapter(movieVideoAdapter);

            if (videoRecyclerView.getVisibility() == View.GONE) {
                videoRecyclerView.setVisibility(View.VISIBLE);
            }
            videoLoadImage.setVisibility(View.GONE);

        }

        @Override
        protected ArrayList<String> doInBackground(String... strings) {
            return MovieUtils.fetchMovieVideo(strings[0]);

        }
    }

    public class MovieReviewAsyncTask extends AsyncTask<String, Void, ArrayList<Reviews>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            if (reviewRecyclerView.getVisibility() == View.VISIBLE) {
                reviewRecyclerView.setVisibility(View.GONE);
            }
            reviewLoadImage.setVisibility(View.VISIBLE);
        }

        @Override
        protected ArrayList<Reviews> doInBackground(String... strings) {

            return MovieUtils.fetchReviewDetails(strings[0]);
        }

        @Override
        protected void onPostExecute(ArrayList<Reviews> movieReviews) {
            super.onPostExecute(movieReviews);

            movieReviewAdapter = new ReviewsAdapter(Movie_Detail_Activity.this, movieReviews);
            reviewRecyclerView.setAdapter(movieReviewAdapter);

            if (reviewRecyclerView.getVisibility() == View.GONE) {
                reviewRecyclerView.setVisibility(View.VISIBLE);
                reviewLoadImage.setVisibility(View.GONE);

            }
        }
    }
}
