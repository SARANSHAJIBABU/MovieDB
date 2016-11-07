package com.saran.mdb;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Saran on 2/11/16.
 */

public interface ApiInterface {

    @GET("movie/now_playing")
    Call<LatestMovies> getTopLatestMovies(@Query("api_key")String apiKey, @Query("language") String language);

    @GET("movie/{movie_id}")
    Call<LatestMovies.Result> getMovieDetails(@Path("movie_id") String movieId,@Query("api_key")String apiKey, @Query("language") String language);

}
