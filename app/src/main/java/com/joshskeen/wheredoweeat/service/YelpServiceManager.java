package com.joshskeen.wheredoweeat.service;

import com.joshskeen.wheredoweeat.model.Business;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by joshskeen on 10/3/14.
 */
public class YelpServiceManager {

    private YelpService mYelpService;

    public YelpServiceManager(YelpService yelpService) {
        mYelpService = yelpService;
    }

    public Observable<Business> performSearch() {
        return mYelpService.search("pizza")
                .subscribeOn(Schedulers.newThread()) //specify which Scheduler an Observable should use when its subscription is invoked
                .observeOn(AndroidSchedulers.mainThread()) //specify on which Scheduler a Subscriber should observe the Observable
                .flatMap(searchResponse -> Observable.from(searchResponse.mBusinesses))
                .filter(business -> business.mRating >= 3.5); //filter out businesses with ratings below 3.5
    }


}
