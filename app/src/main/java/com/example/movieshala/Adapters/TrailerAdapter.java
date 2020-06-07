package com.example.movieshala.Adapters;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.movieshala.R;

import java.util.ArrayList;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TrailerAdapter extends RecyclerView.Adapter<TrailerAdapter.TrailerViewHolder> {

    private ArrayList<String> movieLinkList;
    private Context mContext;

    public TrailerAdapter(ArrayList<String> movieLinkList, Context mContext) {
        this.movieLinkList = movieLinkList;
        this.mContext = mContext;
    }

    public class TrailerViewHolder extends RecyclerView.ViewHolder {
        private TextView trailerView;

        public TrailerViewHolder(@NonNull View itemView) {
            super(itemView);
            trailerView = itemView.findViewById(R.id.tv_trailer);


        }
    }


    @NonNull
    @Override
    public TrailerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(mContext).inflate(R.layout.trailer_layout,parent,false);
        return new TrailerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrailerViewHolder holder, final int position) {
        holder.trailerView.setText("Trailer " + String.valueOf(position + 1));
        holder.trailerView.setOnClickListener(new View.OnClickListener() {
        String id =movieLinkList.get(position);
            @Override
            public void onClick(View v) {
                Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(id));
                try {
                    mContext.startActivity(webIntent);
                } catch (ActivityNotFoundException ex) {
                    mContext.startActivity(webIntent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieLinkList.size();
    }
}
