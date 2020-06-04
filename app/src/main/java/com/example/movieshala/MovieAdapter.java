package com.example.movieshala;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
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
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.title.setText(mMovieDetailsList.get(position).getMovieTitle());
        holder.rating.setRating(mMovieDetailsList.get(position).getMovieRatings().floatValue());
        String link="http://image.tmdb.org/t/p/w185/"+mMovieDetailsList.get(position).getMovieImage();

        Picasso.get().load(link).into(holder.imageView);

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
