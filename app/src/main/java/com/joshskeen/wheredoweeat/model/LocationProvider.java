package com.joshskeen.wheredoweeat.model;

import android.content.Context;
import android.location.Location;

import io.nlopez.smartlocation.SmartLocation;
import rx.subjects.BehaviorSubject;

/**
 * Created by joshskeen on 10/3/14.
 */
public class LocationProvider {

    private BehaviorSubject<Location> mBehaviorSubject = BehaviorSubject.create();
    private Context mContext;
    private Location mLocation;

    public LocationProvider(Context context, Location location) {
        mContext = context;
        mLocation = location;
    }

    public void loadLocation() {
        if (mLocation != null) {
            mBehaviorSubject.onNext(mLocation);
            return;
        }
        loadNewLocation();
    }

    public BehaviorSubject<Location> getBehaviorSubject() {
        return mBehaviorSubject;
    }

    public void loadNewLocation() {
        SmartLocation.getInstance().start(mContext);
        SmartLocation.getInstance().setOnLocationUpdatedListener((location, detectedActivity) -> {
            mLocation = location;
            SmartLocation.getInstance().stop(mContext);
            mBehaviorSubject.onNext(location);
        });
    }

    public Location getLocation() {
        return mLocation;
    }
}