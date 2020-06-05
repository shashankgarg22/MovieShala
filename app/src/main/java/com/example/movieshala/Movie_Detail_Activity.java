package com.example.movieshala;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Movie_Detail_Activity extends AppCompatActivity {
    String mid;
    String title;
    String overView;
    String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie__detail_);
        Intent intent=getIntent();
        updateDetails(intent);
    }

    @SuppressLint("SetTextI18n")
    private void updateDetails(Intent intent) {
        title = intent.getStringExtra("title");
        overView = intent.getStringExtra("overview");
        date = intent.getStringExtra("date");
        Bundle b = getIntent().getExtras();
        double rating = b.getDouble("key");
        //rating=intent.getDoubleExtra(String.valueOf(rating),0);
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
        ratingTextView.setText(getString(R.string.rate) + rating+ getString(R.string.out));
        overViewTextView.setText(overView);

        Picasso.get().load(image).placeholder(R.drawable.load5).into(imageView);
    }
}