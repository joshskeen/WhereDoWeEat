package com.joshskeen.wheredoweeat.model;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.annotations.SerializedName;

/**
 * Created by joshskeen on 10/6/14.
 */
public class Coordinate {

    @SerializedName("latitude")
    public double mLatitude;

    @SerializedName("longitude")
    public double mLongitude;

    public LatLng toLatLng() {
        return new LatLng(mLatitude, mLongitude);
    }

    @Override
    public String toString() {
        return "Coordinate{" +
                "mLatitude=" + mLatitude +
                ", mLongitude=" + mLongitude +
                '}';
    }
}
