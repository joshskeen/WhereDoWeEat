package com.joshskeen.wheredoweeat.service;

import com.joshskeen.wheredoweeat.model.Business;
import com.joshskeen.wheredoweeat.model.LocationProvider;

import java.util.Random;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class YelpServiceManager {

    private YelpService mYelpService;

    public YelpServiceManager(YelpService yelpService, LocationProvider locationProvider) {
        mYelpService = yelpService;
        mYelpService.setLocationProvider(locationProvider);
    }

    public Observable<Business> performSearch() {
        return mYelpService.search("food")
                .subscribeOn(Schedulers.newThread()) //specify which Scheduler an Observable should use when its subscription is invoked
                .observeOn(AndroidSchedulers.mainThread()) //specify on which Scheduler a Subscriber should observe the Observable
                .flatMap(searchResponse -> Observable.from(searchResponse.mBusinesses))
                .filter(business -> business.mRating >= 3.5) //filter out businesses with ratings below 3.5
                .toList()
                .map(businesses -> businesses.get(new Random().nextInt(businesses.size())));
    }
}