package com.example.android.popularmoviesapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import org.json.JSONException;
import java.io.IOException;
import java.net.URL;
import java.util.*;

import butterknife.BindView;
import butterknife.ButterKnife;
import utilities.JsonUtils;
import utilities.NetworkUtils;

public class MainActivity extends AppCompatActivity {

    private static final String TOP_RATED = "top_rated";
    private static final String POPULAR = "popular";
    private static final String NETWORK_ERROR = "NO Network Connection!";
    private static List<Movie> mMovies = new ArrayList<>();

    @BindView(R.id.rv_movies)
    RecyclerView recyclerView;

    private MovieAdapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setUpRecyclerView();
    }

    private void setUpRecyclerView()
    {
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        movieAdapter = new MovieAdapter(mMovies,this);
        recyclerView.setAdapter(movieAdapter);

        loadMovieData(TOP_RATED);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.top_rated:
                loadMovieData(TOP_RATED);
                break;
            case R.id.most_pop:
                loadMovieData(POPULAR);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
    private void loadMovieData(String sortOrder)
    {
        if (NetworkUtils.isNetworkConnection( getApplicationContext()))
        {
            MovieAsyncTask task = new MovieAsyncTask();
            task.execute(sortOrder);
        }
        else
        {
            Toast.makeText(this, NETWORK_ERROR, Toast.LENGTH_LONG).show();
        }
    }
    class MovieAsyncTask extends AsyncTask<String, Void, String>
    {

        @Override
        protected String doInBackground(String... params) {
            URL url = NetworkUtils.buildUrl(params[0]);
            String result = null;

            try {
                result = NetworkUtils.getResponseFromHttpUrl(url);
            } catch (IOException e) {
                e.printStackTrace();
            }

            String movieData = null;

            try {
                movieData = JsonUtils.getFromJSON(result);

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return movieData;
        }

        @Override
        protected void onPostExecute(String s) {
            try {
                mMovies.clear();
                mMovies.addAll(JsonUtils.getMoviesArray(s));
                movieAdapter.notifyDataSetChanged();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}