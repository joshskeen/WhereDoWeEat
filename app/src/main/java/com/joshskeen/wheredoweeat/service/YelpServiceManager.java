package com.joshskeen.wheredoweeat.service;

import com.joshskeen.wheredoweeat.model.Business;
import com.joshskeen.wheredoweeat.model.LocationProvider;

import retrofit.RetrofitError;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subjects.BehaviorSubject;

/**
 * Created by joshskeen on 10/3/14.
 */
public class YelpServiceManager {

    private YelpService mYelpService;
    private BehaviorSubject<RetrofitError> mErrorBehaviorSubject = BehaviorSubject.create();

    public YelpServiceManager(YelpService yelpService, LocationProvider locationProvider) {
        mYelpService = yelpService;
        mYelpService.setBehaviorSubject(mErrorBehaviorSubject);
        mYelpService.setLocationProvider(locationProvider);
    }

    public BehaviorSubject<RetrofitError> getErrorBehaviorSubject() {
        return mErrorBehaviorSubject;
    }

    public Observable<Business> performSearch() {
        return mYelpService.search("pizza")
                .subscribeOn(Schedulers.newThread()) //specify which Scheduler an Observable should use when its subscription is invoked
                .observeOn(AndroidSchedulers.mainThread()) //specify on which Scheduler a Subscriber should observe the Observable
                .flatMap(searchResponse -> Observable.from(searchResponse.mBusinesses))
                .filter(business -> business.mRating >= 3.5); //filter out businesses with ratings below 3.5
    }


}
