package com.example.android.popularmoviesapp;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Jason on 6/7/2018.
 */

public class Movie  implements Parcelable {
    private String mTitle;
    private String mDescription;
    private String mReleaseDate;
    private String mImageUrl;
    private String mRating;

    public Movie(int i)
    {
        this.mId = i;
    }
   /* public Movie(String mTitle, String mDescription, String mReleaseDate, String mImageUrl, String mRating) {
        this.mTitle = mTitle;
        this.mDescription = mDescription;
        this.mReleaseDate = mReleaseDate;
        this.mImageUrl = mImageUrl;
        this.mRating = mRating;
    }
*/
    private Movie(Parcel in) {
        mId = in.readInt();
        mTitle = in.readString();
        mImageUrl = in.readString();
        mDescription = in.readString();
        mRating = in.readString();
        mReleaseDate = in.readString();
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getReleaseDate() {
        return mReleaseDate;
    }

    public void setReleaseDate(String mReleaseDate) {
        this.mReleaseDate = mReleaseDate;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }

    public String getRating() {
        return mRating;
    }

    public void setRating(String mRating) {
        this.mRating = mRating;
    }

    public int getId() {
        return mId;
    }

    public void setId(int mId) {
        this.mId = mId;
    }

    int mId;





    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int num) {
        parcel.writeInt(mId);
        parcel.writeString(mTitle);
        parcel.writeString(mImageUrl);
        parcel.writeString(mDescription);
        parcel.writeString(mRating);
        parcel.writeString(mReleaseDate);
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>()
    {
        @Override
        public Movie createFromParcel(Parcel p)
        {
            return new Movie(p);
        }
        @Override
        public Movie[] newArray(int len)
        {
            return new Movie[len];
        }
    };
}
