package com.joshskeen.wheredoweeat.model;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.util.Log;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import io.nlopez.smartlocation.SmartLocation;
import rx.subjects.BehaviorSubject;

/**
 * Created by joshskeen on 10/3/14.
 */
public class LocationProvider {

    private static final String TAG = "LocationProvider";
    private BehaviorSubject<Location> mBehaviorSubject = BehaviorSubject.create();
    private Context mContext;
    private Location mLocation;
    private String mCity = "Foobar";

    public LocationProvider(Context context) {
        mContext = context;
        mLocation = SmartLocation.getInstance().getLastKnownLocation(mContext);
    }

    public void loadLocation() {
        if (mLocation != null) {
            geocodeLocation();
            mBehaviorSubject.onNext(mLocation);
            return;
        }
        SmartLocation.getInstance().start(mContext);
        SmartLocation.getInstance().setOnLocationUpdatedListener((location, detectedActivity) -> {
            mLocation = location;
            geocodeLocation();
            SmartLocation.getInstance().stop(mContext);
            mBehaviorSubject.onNext(location);
        });
    }

    private void geocodeLocation() {
        Geocoder gcd = new Geocoder(mContext, Locale.getDefault());
        List<Address> fromLocation = null;
        try {
            Log.d(TAG, "location lat & lon " + mLocation.getLatitude() + ", " + mLocation.getLongitude());
            fromLocation = gcd.getFromLocation(mLocation.getLatitude(), mLocation.getLongitude(), 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (fromLocation != null) {
            mCity = fromLocation.get(0).getLocality();
        }
    }

    public BehaviorSubject<Location> getBehaviorSubject() {
        return mBehaviorSubject;
    }

    public Location getLocation() {
        return mLocation;
    }

    public String getCity() {
        return mCity;
    }
}