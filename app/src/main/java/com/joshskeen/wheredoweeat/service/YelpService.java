package com.joshskeen.wheredoweeat.service;


import android.location.Location;

import com.joshskeen.wheredoweeat.BuildConfig;
import com.joshskeen.wheredoweeat.inject.Preference;
import com.joshskeen.wheredoweeat.model.LocationProvider;
import com.joshskeen.wheredoweeat.model.StringUtil;
import com.joshskeen.wheredoweeat.service.oauth.RetrofitHttpOAuthConsumer;
import com.joshskeen.wheredoweeat.service.oauth.SigningOkClient;
import com.joshskeen.wheredoweeat.service.response.SearchResponse;

import retrofit.RestAdapter;
import rx.Observable;

/**
 * Created by joshskeen on 10/2/14.
 */
public class YelpService {

    private static final String TAG = "YelpService";
    private static final Integer YELP_SORT_DISTANCE = 1;
    public static final int LIMIT = 20;
    //OAuth-related
    public static String OAUTH_CONSUMER_KEY = BuildConfig.OAUTH_CONSUMER_KEY;
    public static String OAUTH_CONSUMER_SECRET = BuildConfig.OAUTH_CONSUMER_SECRET;
    public static String OAUTH_TOKEN = BuildConfig.OAUTH_TOKEN;
    public static String OAUTH_TOKEN_SECRET = BuildConfig.OAUTH_TOKEN_SECRET;
    //Yelp API endpoint
    public static String YELP_ENDPOINT = "http://api.yelp.com/v2";

    private final YelpServiceInterface mServiceInterface;
    private LocationProvider mLocationProvider;
    private Preference mPreference = new Preference();

    public static YelpService newInstance() {
        RetrofitHttpOAuthConsumer retrofitHttpOAuthConsumer = new RetrofitHttpOAuthConsumer(OAUTH_CONSUMER_KEY, OAUTH_CONSUMER_SECRET);
        retrofitHttpOAuthConsumer.setTokenWithSecret(OAUTH_TOKEN, OAUTH_TOKEN_SECRET);
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(YELP_ENDPOINT)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setClient(new SigningOkClient(retrofitHttpOAuthConsumer))
                .build();
        return new YelpService(restAdapter.create(YelpServiceInterface.class));
    }

    private YelpService(YelpServiceInterface serviceInterface) {
        mServiceInterface = serviceInterface;
    }

    private String formatLatLongString() {
        Location location = mLocationProvider.getLocation();
        return StringUtil.formatLatLng(location.getLatitude(), location.getLongitude());
    }

    public Observable<SearchResponse> search(String query) {
        return mServiceInterface.search(formatLatLongString(),
                query,
                LIMIT,
                mPreference.getRatingThreshhold(),
                YELP_SORT_DISTANCE,
                false,
                mPreference.getDistanceThreshhold());
    }

    public void setLocationProvider(LocationProvider locationProvider) {
        mLocationProvider = locationProvider;
    }

    public void setPreference(Preference preference) {
        mPreference = preference;
    }
}