package com.example.android.popularmoviesapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.android.popularmoviesapp.MovieAdapter.MOVIE_EXTRA;

public class detailedActivity extends AppCompatActivity {

    @BindView(R.id.movie_image_detail_activityIV)
    ImageView mImage;
    @BindView(R.id.titleTV)
    TextView mTitleTV;
    @BindView(R.id.description_TV)
    TextView mDescriptionTV;
    @BindView(R.id.release_date_TV)
    TextView mReleaseDateTV;
   @BindView(R.id.rating_TV)
   TextView mRatingTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);
        ButterKnife.bind(this);

            Intent intent = getIntent();

        if (intent.hasExtra(MOVIE_EXTRA))
            {
                Movie mMovie = intent.getParcelableExtra(MOVIE_EXTRA);

                Picasso.with(this).load(mMovie.getImageUrl()).into(mImage);
                mTitleTV.setText(mMovie.getTitle());
                mReleaseDateTV.setText(mMovie.getReleaseDate());
                mRatingTV.setText(mMovie.getRating());
                mDescriptionTV.setText(mMovie.getDescription());
            }
            else
        {
            String error = "Intent has no EXTRAS";
            Toast.makeText(getApplicationContext(), error, Toast.LENGTH_LONG).show();
        }
    }
}
