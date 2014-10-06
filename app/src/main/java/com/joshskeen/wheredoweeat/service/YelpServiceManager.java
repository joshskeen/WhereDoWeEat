package com.joshskeen.wheredoweeat.service;

import android.util.Log;

import com.joshskeen.wheredoweeat.inject.Preference;
import com.joshskeen.wheredoweeat.model.Business;
import com.joshskeen.wheredoweeat.model.LocationProvider;

import java.util.Random;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class YelpServiceManager {

    private static final String TAG = "YelpServiceManager";
    private YelpService mYelpService;
    private Preference mPreference;

    public YelpServiceManager(YelpService yelpService, LocationProvider locationProvider, Preference preference) {
        mYelpService = yelpService;
        mPreference = preference;
        mYelpService.setPreference(preference);
        mYelpService.setLocationProvider(locationProvider);
    }

    public Observable<Business> performSearch() {
        Log.d(TAG, "filter with rating of at least " + mPreference.getRatingThreshhold() + ", radius = " + mPreference.getDistanceThreshhold());
        return mYelpService.search("food")
                .subscribeOn(Schedulers.newThread()) //specify which Scheduler an Observable should use when its subscription is invoked
                .observeOn(AndroidSchedulers.mainThread()) //specify on which Scheduler a Subscriber should observe the Observable
                .flatMap(searchResponse -> Observable.from(searchResponse.mBusinesses))
                .filter(business -> business.mLocation.mCoordinate != null)
                .filter(business -> business.mClosed != true) //filter out businesses that are closed!
                .filter(business -> business.mRating >= mPreference.getRatingThreshhold()) //filter out businesses with ratings below a certain value
                .toList()
                .map(businesses -> businesses.get(new Random().nextInt(businesses.size())));
    }
}