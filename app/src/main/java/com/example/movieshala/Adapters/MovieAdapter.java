package com.example.movieshala.Adapters;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.movieshala.Movie_Detail_Activity;
import com.example.movieshala.objects.MovieDetail;
import com.example.movieshala.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MovieAdapter  extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> {

    private Context mContext;
    private List<MovieDetail> mMovieDetailsList;

    public MovieAdapter(Context context,List<MovieDetail> movieDetailsList){
        mContext=context;
        mMovieDetailsList=movieDetailsList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater layoutInflater= LayoutInflater.from(mContext);
        view= layoutInflater.inflate(R.layout.item_layout,parent,false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        holder.title.setText(mMovieDetailsList.get(position).getMovieTitle());
        holder.rating.setRating(mMovieDetailsList.get(position).getMovieRatings().floatValue());
        final String link="http://image.tmdb.org/t/p/w185/"+mMovieDetailsList.get(position).getMovieImage();
        final String backLink="http://image.tmdb.org/t/p/w185/"+mMovieDetailsList.get(position).getBackimage();
        Picasso.get().load(link).placeholder(R.drawable.load5).into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext, Movie_Detail_Activity.class);
                MovieDetail movieDetails=mMovieDetailsList.get(position);
                intent.putExtra("title", movieDetails.getMovieTitle());
                Bundle b = new Bundle();
                b.putDouble("key", movieDetails.getMovieRatings());
                intent.putExtras(b);
                intent.putExtra("overview", movieDetails.getMovieSynopsis());
                intent.putExtra("date", movieDetails.getMovieDate());
                intent.putExtra("image", backLink);
                intent.putExtra("id", movieDetails.getMovieId());
                mContext.startActivity(intent);
            }
        });

        Log.i("RecyclerView-output",link);

    }

    @Override
    public int getItemCount() {
        return mMovieDetailsList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        RatingBar rating;
        ImageView imageView;

        public MyViewHolder(View itemView){
            super(itemView);

            title=itemView.findViewById(R.id.movie_tittle);
            rating=itemView.findViewById(R.id.rating);
            imageView=itemView.findViewById(R.id.movie_image);

        }
    }
}
