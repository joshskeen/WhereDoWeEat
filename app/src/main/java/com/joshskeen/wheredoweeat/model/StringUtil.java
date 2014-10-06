package com.joshskeen.wheredoweeat.model;

/**
 * Created by joshskeen on 10/6/14.
 */
public class StringUtil {

    public static String formatLatLng(double lat, double lon){
        return String.format("%.6f,%.6f", lat, lon);
    }

    public static String formatLatLng(Location location){
        return formatLatLng(location.mCoordinate.mLatitude, location.mCoordinate.mLongitude);
    }

    public static String formatLatLng(android.location.Location location) {
        return formatLatLng(location.getLatitude(), location.getLongitude());
    }
}
