package com.example.movieshala.Adapters;

import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.movieshala.R;
import com.example.movieshala.objects.Reviews;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import ru.embersoft.expandabletextview.ExpandableTextView;

public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.ReviewViewHolder> {

    private Context mContext;
    private ArrayList<Reviews> reviews;

    public ReviewsAdapter(Context mContext, ArrayList<Reviews> reviews) {
        this.mContext = mContext;
        this.reviews = reviews;
    }

    public static class ReviewViewHolder extends RecyclerView.ViewHolder{

        TextView mAuthor;
        ExpandableTextView mAuthorReview;

        public ReviewViewHolder(@NonNull View itemView) {
            super(itemView);
            mAuthor=itemView.findViewById(R.id.author);
            mAuthorReview=itemView.findViewById(R.id.author_review);
        }
    }

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.reviews_layout,parent,false);
        return new ReviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder holder, final int position) {
            final Reviews item =reviews.get(position);
            holder.mAuthor.setText("Author:"+reviews.get(position).getmReviewAuthor());
            holder.mAuthorReview.setText(reviews.get(position).getmReviewDetail());
            holder.mAuthorReview.setOnStateChangeListener(new ExpandableTextView.OnStateChangeListener() {
                @Override
                public void onStateChange(boolean isShrink) {
                    Reviews contentItem=reviews.get(position);
                    contentItem.setShrink(isShrink);
                    reviews.set(position,contentItem);
                }
            });
            holder.mAuthorReview.setText(reviews.get(position).getmReviewDetail());
            holder.mAuthorReview.resetState(item.isShrink());
    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }
}
