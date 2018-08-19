package com.example.android.popularmoviesapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;
import java.util.*;

/**
 * Created by Jason on 6/1/2018.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder>
{
    private List<Movie> mMovies;
    private  Context mContext;
    public static String MOVIE_EXTRA = "MOVIE";

    public interface ListItemClickedListener
    {
        void onListItemClicked(Movie movie);
    }

    public MovieAdapter(List<Movie> movies,Context context)
    {
        mMovies= movies;
        mContext = context;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.movies_list_item, parent,false);

      return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position)
    {
        Picasso.with(holder.itemView.getContext()).load(mMovies.get(position).getImageUrl())
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        if (mMovies == null)
            return 0;
        return mMovies.size();
    }

    // TODO The ViewHolder nested class
     class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        ImageView imageView;

         MovieViewHolder(View viewItem)
        {
            super(viewItem);
            imageView = viewItem.findViewById(R.id.iv_list_image_view);
            viewItem.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent  = new Intent(mContext, detailedActivity.class);
            intent.putExtra(MOVIE_EXTRA, mMovies.get(getAdapterPosition()));
            mContext.startActivity(intent);
        }
    }
}