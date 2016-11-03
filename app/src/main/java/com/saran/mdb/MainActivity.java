package com.saran.mdb;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
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
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new SimpleDividerItemDecorator(this));
        ApiInterface apiInterface = ApiCLient.getClient().create(ApiInterface.class);

        Call<LatestMovies> call = apiInterface.getTopLatestMovies(Constants.API_KEY,Constants.API_LANGUAGE);
        call.enqueue(new Callback<LatestMovies>() {
            @Override
            public void onResponse(Call<LatestMovies> call, Response<LatestMovies> response) {
                if(response!=null){
                    LatestMovies movies = response.body();
                    mRecyclerView.setAdapter(new MoviesAdapter(movies.getResults(),R.layout.row_main));
                    Toast.makeText(MainActivity.this,"Success",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LatestMovies> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Failure",Toast.LENGTH_SHORT).show();
            }
        });
    }

    class SimpleDividerItemDecorator extends RecyclerView.ItemDecoration{
        private Drawable divider;

        public SimpleDividerItemDecorator(Context context){
            divider = ContextCompat.getDrawable(context,R.drawable.line_divider);
        }

        @Override
        public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
            int left = parent.getPaddingLeft();
            int right = parent.getWidth() - parent.getPaddingRight();
            int childCount = parent.getChildCount();

            for(int i=0; i<childCount; i++){
                View child = parent.getChildAt(i);
                RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
                int top = child.getBottom()+params.bottomMargin;
                int bottom = top+divider.getIntrinsicHeight();
                divider.setBounds(left,top,right,bottom);
                divider.draw(c);
            }
        }
    }
}
