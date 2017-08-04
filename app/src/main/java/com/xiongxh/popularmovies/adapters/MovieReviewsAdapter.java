package com.xiongxh.popularmovies.adapters;


import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.xiongxh.popularmovies.R;
import com.xiongxh.popularmovies.utilities.ConstantsUtils;

public class MovieReviewsAdapter extends RecyclerView.Adapter<MovieReviewsAdapter.ReviewViewHolder>{
    private static final String TAG = MovieReviewsAdapter.class.getSimpleName();

    private final Context mContext;
    private Cursor mCursor;

    public MovieReviewsAdapter (Context context){
        mContext = context;
    }

    @Override
    public ReviewViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        Context context = viewGroup.getContext();
        if (viewGroup instanceof RecyclerView) {
            int layoutIdForListItem = R.layout.list_item_review;
            LayoutInflater inflater = LayoutInflater.from(context);
            boolean shouldAttachToParentImmediately = false;

            View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
            view.setFocusable(true);

            ReviewViewHolder reviewViewHolder = new ReviewViewHolder(view);

            return reviewViewHolder;

        }else {
            throw new RuntimeException("Not bound to Recyclerview.");
        }
    }

    @Override
    public void onBindViewHolder(ReviewViewHolder reviewHolder, int position) {
        Log.d(TAG, "#" + position);
        if (! mCursor.moveToFirst()){
            return;
        }

        mCursor.moveToPosition(position);

        String reviewContent = mCursor.getString(ConstantsUtils.COLUMN_REVIEW_CONTENT);
        reviewHolder.mReviewContentView.setText(reviewContent);

        String reviewAuthor = mCursor.getString(ConstantsUtils.COLUMN_REVIEW_AUTHOR);
        reviewHolder.mReviewAuthorView.setText(reviewAuthor);

    }

    @Override
    public int getItemCount() {
        if (null == mCursor) {
            return 0;
        }
        return mCursor.getCount();
    }

    public void swapCursor(Cursor cursor){
        mCursor = cursor;
        notifyDataSetChanged();
        //mEmptyView.setVisibility(getItemCount() == 0 ? View.VISIBLE : View.GONE);
    }


    public class ReviewViewHolder extends RecyclerView.ViewHolder {
        private TextView mReviewContentView;
        private TextView mReviewAuthorView;

        public ReviewViewHolder(View itemView){
            super(itemView);

            mReviewContentView = (TextView) itemView.findViewById(R.id.tv_movie_review_content);

            mReviewAuthorView = (TextView) itemView.findViewById(R.id.tv_movie_review_author);
        }
    }
}

//public class MovieReviewsAdapter extends CursorAdapter{
//    private final String LOG_TAG = MovieReviewsAdapter.class.getSimpleName();
//
//    public MovieReviewsAdapter(Context context, Cursor cursor, int flag){
//        super(context, cursor, flag);
//    }
//
//    @Override
//    public View newView(Context context, Cursor cursor, ViewGroup parent){
//        Log.d(LOG_TAG, "Entering newView...");
//        View view = LayoutInflater.from(context).inflate(R.layout.list_item_review, parent, false);
//
//        return view;
//    }
//
//    @Override
//    public void bindView(View view, Context context, Cursor cursor){
//        Log.d(LOG_TAG, "Entering bindView...");
//        TextView movieReviewContent = (TextView) view.findViewById(R.id.tv_movie_review_content);
//        Log.d(LOG_TAG, "1: Error here?");
//        movieReviewContent.setText(cursor.getString(ConstantsUtils.COLUMN_REVIEW_CONTENT));
//        Log.d(LOG_TAG, "2: Error here?");
//
//        TextView movieReviewAuthor = (TextView) view.findViewById(R.id.tv_movie_review_author);
//        Log.d(LOG_TAG, "3: Error here?");
//        movieReviewAuthor.setText(cursor.getString(ConstantsUtils.COLUMN_REVIEW_AUTHOR));
//        Log.d(LOG_TAG, "Exit bindView.");
//    }
//}
