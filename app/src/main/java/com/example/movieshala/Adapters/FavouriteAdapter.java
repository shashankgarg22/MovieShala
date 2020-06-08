package com.example.movieshala.Adapters;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.movieshala.R;
import com.example.movieshala.objects.FavouriteDetails;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.favouriteViewHolder> {

    private List<FavouriteDetails> list = new ArrayList<>();

    @NonNull
    @Override
    public favouriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.favourite, parent, false);
        return new favouriteViewHolder(view);

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull favouriteViewHolder holder, int position) {
        holder.title.setText(list.get(position).getTitle());
        holder.date.setText("Release Date: "+list.get(position).getDate());
        String result=new Double(list.get(position).getRating()).toString();
        holder.rating.setText("Ratings: "+result);
        Log.i(TAG, "onBindViewHolder:  "+list.get(position).getRating());
        holder.description.setText(list.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(List<FavouriteDetails> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public FavouriteDetails getItemAt(int position) {
        return list.get(position);
    }

    public class favouriteViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private TextView description;
        private TextView date;
        private TextView rating;

        public favouriteViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.favourite_title);
            date = itemView.findViewById(R.id.favourite_date);
            description = itemView.findViewById(R.id.favourite_description);
            rating = itemView.findViewById(R.id.favourite_rating);
        }

    }
}
