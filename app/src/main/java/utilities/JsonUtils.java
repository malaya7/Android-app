package utilities;

import com.example.android.popularmoviesapp.Movie;
import org.json.*;
import java.util.*;


public class JsonUtils
{
    private static final String BASE_IMAGE_URL = "https://image.tmdb.org/t/p/";
    private static final String IMAGE_SIZE = "w185";
    private static final String SPLIT = "-";

    public static String getFromJSON(String json) throws JSONException {

        JSONObject jsonObject = new JSONObject(json);
        String ArrayName = "results";
        JSONArray movies = jsonObject.getJSONArray(ArrayName);
        return movies.toString();

    }

    public static ArrayList<Movie> getMoviesArray(String jsonMovies) throws JSONException {
        ArrayList<Movie> movies = new ArrayList<>();
        JSONArray jsonArrayMovies = new JSONArray(jsonMovies);
        for (int i= 0; i < jsonArrayMovies.length(); i++) {

            movies.add(parseMovieObject(jsonArrayMovies.getJSONObject(i)));
        }

        return movies;
    }

    private static Movie parseMovieObject(JSONObject jsonObject) throws JSONException
    {
        final String ID = "id";
        final String POSTER_PATH = "poster_path";
        final String TITLE = "title";
        final String OVERVIEW = "overview";
        final String RELEASE_DATE = "release_date";
        final String VOTE_AVERAGE = "vote_average";

        int movieId = jsonObject.getInt(ID);
        Movie movie = new Movie(movieId);

        if (jsonObject.has(TITLE)) {
            movie.setTitle(jsonObject.getString(TITLE));
        }
        if (jsonObject.has(POSTER_PATH)) {
            movie.setImageUrl(BASE_IMAGE_URL + IMAGE_SIZE + jsonObject.getString(POSTER_PATH));
        }
        if (jsonObject.has(OVERVIEW)) {
            movie.setDescription(jsonObject.getString(OVERVIEW));
        }
        if (jsonObject.has(RELEASE_DATE)) {
            String formatDate = jsonObject.getString(RELEASE_DATE);
            String date[] = formatDate.split(SPLIT, 3);
            movie.setReleaseDate(date[0]);
        }
        if (jsonObject.has(VOTE_AVERAGE)) {
            movie.setRating(jsonObject.getString(VOTE_AVERAGE));
        }
        return movie;
    }
}
