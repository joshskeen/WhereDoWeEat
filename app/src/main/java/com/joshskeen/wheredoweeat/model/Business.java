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

    @Override
    public String toString() {
        return "Business{" +
                "mName='" + mName + '\'' +
                ", mRating=" + mRating +
                '}';
    }
}