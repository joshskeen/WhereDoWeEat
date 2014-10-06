package com.joshskeen.wheredoweeat.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by joshskeen on 10/2/14.
 */
public class Business {

    @SerializedName("name")
    public String mName;

    @SerializedName("rating")
    public double mRating;

    @SerializedName("distance")
    public double mDistance;

    @SerializedName("image_url")
    public String mImageUrl;

    @SerializedName("location")
    public Location mLocation;

    @SerializedName("closed")
    public boolean mClosed;

    public String approxDistanceInMiles() {
        double miles = mDistance * 0.00062137;
        return String.format("about %.2f miles away", miles);
    }

    @Override
    public String toString() {
        return "Business{" +
                "mName='" + mName + '\'' +
                ", mRating=" + mRating +
                ", mLocation=" + mLocation +
                '}';
    }

}