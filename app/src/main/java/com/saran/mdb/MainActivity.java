package com.saran.mdb;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler);

        ApiInterface apiInterface = ApiCLient.getClient().create(ApiInterface.class);

        Call<LatestMovies> call = apiInterface.getTopLatestMovies(Constants.API_KEY,Constants.API_LANGUAGE);
        call.enqueue(new Callback<LatestMovies>() {
            @Override
            public void onResponse(Call<LatestMovies> call, Response<LatestMovies> response) {
                if(response!=null){
                    Toast.makeText(MainActivity.this,"Success",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LatestMovies> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Failure",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
