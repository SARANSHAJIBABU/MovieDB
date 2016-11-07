package com.saran.mdb;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        int id = getIntent().getIntExtra("movie_id",-1);
        final ImageView imageView = (ImageView) findViewById(R.id.iv_detail_top);
        final TextView tv = (TextView) findViewById(R.id.tv_detail_description);

        Log.d("Detail","movie_id "+id);

        ApiInterface apiInterface = ApiCLient.getClient().create(ApiInterface.class);

        Call<LatestMovies.Result> call = apiInterface.getMovieDetails(id+"",Constants.API_KEY,Constants.API_LANGUAGE);
        call.enqueue(new Callback<LatestMovies.Result>() {
            @Override
            public void onResponse(Call<LatestMovies.Result> call, Response<LatestMovies.Result> response) {
                Log.d("Detail","Success");
                Glide.with(DetailActivity.this).load("http://image.tmdb.org/t/p/original"+response.body().getBackdropPath()).into(imageView);
                tv.setText(response.body().getOverview());
            }

            @Override
            public void onFailure(Call<LatestMovies.Result> call, Throwable t) {
                Log.d("Detail","Failed");
            }
        });
    }
}
