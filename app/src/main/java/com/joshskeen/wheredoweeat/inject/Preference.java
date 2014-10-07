package com.joshskeen.wheredoweeat.inject;

/**
 * Created by joshskeen on 10/6/14.
 */
public class Preference {

    public static int MINIMUM_DISTANCE_THRESHHOLD = 500;
    public static int MINIMUM_RATING_THRESHHOLD = 0;

    private int mDistanceThreshhold = 1500;
    private int mRatingThreshhold = 2;

    public int getDistanceThreshhold() {
        return mDistanceThreshhold;
    }

    public void setDistanceThreshhold(int distanceThreshhold) {
        mDistanceThreshhold = distanceThreshhold + MINIMUM_DISTANCE_THRESHHOLD;
    }


    public void setRatingThreshhold(int ratingThreshhold) {
        mRatingThreshhold = ratingThreshhold + MINIMUM_RATING_THRESHHOLD;
    }

    public int getRatingThreshhold() {
        return mRatingThreshhold;
    }
}
