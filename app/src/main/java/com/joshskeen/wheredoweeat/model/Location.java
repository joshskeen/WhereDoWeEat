package com.joshskeen.wheredoweeat.model;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;

/**
 * Created by joshskeen on 10/6/14.
 */
public class Location {

    @SerializedName("city")
    public String mCity;

    @SerializedName("state_code")
    public String mStateCode;

    @SerializedName("address")
    public String[] mAddress;

    @SerializedName("postal_code")
    public String mPostalCode;

    @SerializedName("coordinate")
    public Coordinate mCoordinate;

    @Override
    public String toString() {
        return "Location{" +
                "mCity='" + mCity + '\'' +
                ", mStateCode='" + mStateCode + '\'' +
                ", mAddress=" + Arrays.toString(mAddress) +
                ", mPostalCode='" + mPostalCode + '\'' +
                ", mCoordinate=" + mCoordinate +
                '}';
    }
}
