package com.saran.mdb;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


/**
 * Created by Saran on 2/11/16.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder> {

    private List<LatestMovies.Result> mMovies;
    private int mRowLayout;

    public MoviesAdapter(List<LatestMovies.Result> movies, int rowLayout){
        mMovies = movies;
        mRowLayout = rowLayout;
    }

    @Override
    public MoviesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(mRowLayout,parent,false);
        return new MoviesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MoviesViewHolder holder, int position) {
        LatestMovies.Result movie = mMovies.get(position);
        holder.rating.setText(movie.getVoteAverage().toString());
        holder.name.setText(movie.getTitle());
        holder.date.setText(movie.getReleaseDate());
        holder.description.setText(movie.getOverview());
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    public static class MoviesViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView date;
        TextView description;
        ImageView star;
        TextView rating;

        public MoviesViewHolder(View row) {
            super(row);
            name = (TextView) row.findViewById(R.id.tv_main_row_name);
            date = (TextView) row.findViewById(R.id.tv_main_row_date);
            description = (TextView) row.findViewById(R.id.tv_main_row_desc);
            star = (ImageView) row.findViewById(R.id.iv_main_row_star);
            rating = (TextView) row.findViewById(R.id.tv_main_row_rating);
        }
    }


}
