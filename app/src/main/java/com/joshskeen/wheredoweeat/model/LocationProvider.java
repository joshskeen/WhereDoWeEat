package com.joshskeen.wheredoweeat.model;

import android.content.Context;
import android.location.Location;

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

    public LocationProvider(Context context) {
        mContext = context;
        mLocation = SmartLocation.getInstance().getLastKnownLocation(mContext);
    }

    public void loadLocation() {
        if (mLocation != null) {
            mBehaviorSubject.onNext(mLocation);
            return;
        }
        SmartLocation.getInstance().start(mContext);
        SmartLocation.getInstance().setOnLocationUpdatedListener((location, detectedActivity) -> {
            mLocation = location;
            mBehaviorSubject.onNext(location);
            SmartLocation.getInstance().stop(mContext);
        });
    }

    public BehaviorSubject<Location> getBehaviorSubject() {
        return mBehaviorSubject;
    }

    public Location getLocation() {
        return mLocation;
    }
}