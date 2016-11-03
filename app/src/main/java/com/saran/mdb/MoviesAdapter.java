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
    public MoviesViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(mRowLayout,parent,false);
        return new MoviesViewHolder(view, new MoviesViewHolder.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                ((MainActivity)parent.getContext()).onItemClick(view,postion);
            }
        });
    }

    @Override
    public void onBindViewHolder(MoviesViewHolder holder, int position) {
        LatestMovies.Result movie = mMovies.get(position);
        holder.rating.setText(movie.getVoteAverage().toString());
        holder.name.setText(movie.getTitle());
        holder.date.setText(movie.getReleaseDate());
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    public static class MoviesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView name;
        TextView date;
        ImageView star;
        TextView rating;
        OnItemClickListener listener;

        public MoviesViewHolder(View row,OnItemClickListener l) {
            super(row);
            name = (TextView) row.findViewById(R.id.tv_main_row_name);
            date = (TextView) row.findViewById(R.id.tv_main_row_date);
            star = (ImageView) row.findViewById(R.id.iv_main_row_star);
            rating = (TextView) row.findViewById(R.id.tv_main_row_rating);
            row.setOnClickListener(this);
            listener = l;
        }

        @Override
        public void onClick(View v) {
            listener.onItemClick(v,getAdapterPosition());
        }

        public interface OnItemClickListener{
            void onItemClick(View view,int postion);
        }
    }


}
