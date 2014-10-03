package com.joshskeen.wheredoweeat;

import android.location.Location;
import android.os.Bundle;
import android.util.Log;

import com.joshskeen.wheredoweeat.model.LocationProvider;

import javax.inject.Inject;

import rx.subjects.BehaviorSubject;

/**
 * Created by joshskeen on 10/3/14.
 */
public class HomeActivity extends BaseActivity {

    @Inject
    LocationProvider mLocationProvider;
    private BehaviorSubject<Location> mBehaviorSubject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        mLocationProvider.loadLocation();
        mBehaviorSubject = mLocationProvider.getBehaviorSubject();
        mBehaviorSubject.subscribe(location -> Log.d(TAG, "LOADED THE LOCATION!"));
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

}
