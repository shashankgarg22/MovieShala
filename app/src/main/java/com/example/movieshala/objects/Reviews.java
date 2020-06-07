package com.example.movieshala.objects;

public class Reviews {
    private String mReviewAuthor;
    private String mReviewDetail;
    private boolean isShrink=true;

    public Reviews(String mReviewAuthor, String mReviewDetail) {
        this.mReviewAuthor = mReviewAuthor;
        this.mReviewDetail = mReviewDetail;
    }

    public String getmReviewAuthor() {
        return mReviewAuthor;
    }

    public void setmReviewAuthor(String mReviewAuthor) {
        this.mReviewAuthor = mReviewAuthor;
    }

    public String getmReviewDetail() {
        return mReviewDetail;
    }

    public void setmReviewDetail(String mReviewDetail) {
        this.mReviewDetail = mReviewDetail;
    }

    public boolean isShrink() {
        return isShrink;
    }

    public void setShrink(boolean shrink) {
        isShrink = shrink;
    }
}
