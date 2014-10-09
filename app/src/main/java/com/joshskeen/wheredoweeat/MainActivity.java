package com.joshskeen.wheredoweeat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.joshskeen.wheredoweeat.inject.Preference;
import com.joshskeen.wheredoweeat.model.Business;
import com.joshskeen.wheredoweeat.model.LocationProvider;
import com.joshskeen.wheredoweeat.service.WDWESeekBarChangeListener;
import com.joshskeen.wheredoweeat.service.YelpServiceManager;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;


public class MainActivity extends BaseActivity {

    CompositeSubscription mCompositeSubscription = new CompositeSubscription();

    @Inject
    YelpServiceManager mYelpServiceManager;

    @Inject
    LocationProvider mLocationProvider;

    @Inject
    public
    Preference mPreference;

    @InjectView(R.id.business_name)
    TextView mBusinessName;

    @InjectView(R.id.try_again_button)
    Button mTryAgainButton;

    @InjectView(R.id.lets_go_button)
    Button mLetsGoButton;

    @InjectView(R.id.restaurant_preview)
    ImageView mImageView;

    @InjectView(R.id.rating_bar)
    RatingBar mRatingBar;

    @InjectView(R.id.distance_value)
    TextView mDistanceValue;

    @InjectView(R.id.distance_threshhold)
    SeekBar mDistanceThreshhold;

    @InjectView(R.id.rating_threshhold)
    SeekBar mRatingThreshhold;

    MapFragment mMapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        mMapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map_fragment);
        mLetsGoButton.setVisibility(View.GONE);
        setupDistanceAndRatingThreshholdSeekbars();
        loadWhereToEatRecommendation();
        mTryAgainButton.setOnClickListener(v -> loadWhereToEatRecommendation());

    }

    private void setupDistanceAndRatingThreshholdSeekbars() {
        //distance
        mDistanceThreshhold.setProgress(mPreference.getDistanceThreshhold());
        mDistanceThreshhold.setOnSeekBarChangeListener(new WDWESeekBarChangeListener(this) {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mPreference.setDistanceThreshhold(progress);
            }
        });
        //rating
        mRatingThreshhold.setProgress(mPreference.getRatingThreshhold());
        mRatingThreshhold.setOnSeekBarChangeListener(new WDWESeekBarChangeListener(this) {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mPreference.setRatingThreshhold(progress);
            }
        });
    }

    public void loadWhereToEatRecommendation() {
        mBusinessName.setText(getString(R.string.loading));
        Action1<Business> successAction = business -> {
            mLetsGoButton.setVisibility(View.VISIBLE);
            mLetsGoButton.setOnClickListener(v -> startActivity(NavigationIntentBuilder.build(this, mLocationProvider, business)));
            mBusinessName.setText(business.mName);
            Picasso.with(MainActivity.this).load(business.mImageUrl).into(mImageView);
            mDistanceValue.setText(business.approxDistanceInMiles());
            mRatingBar.setRating((float) business.mRating);
            updateMap(business);
        };

        mCompositeSubscription.add(mYelpServiceManager.performSearch().subscribe(successAction, throwable -> {
            mBusinessName.setText(getString(R.string.no_results));
            mMapFragment.getMap().clear();
            mLetsGoButton.setVisibility(View.GONE);
        }));
    }

    private void updateMap(Business business) {
        LatLng position = business.mLocation.mCoordinate.toLatLng();
        MarkerOptions markerOptions = new MarkerOptions().position(position);
        markerOptions.title(business.mName);
        mMapFragment.getMap().clear();
        mMapFragment.getMap().addMarker(markerOptions);
        mMapFragment.getMap().animateCamera(CameraUpdateFactory.newLatLngZoom(business.mLocation.mCoordinate.toLatLng(), 15));
    }

    @Override
    protected void onStart() {
        super.onStart();
        mCompositeSubscription = new CompositeSubscription();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mCompositeSubscription.clear();
        mCompositeSubscription.unsubscribe();
    }
}
