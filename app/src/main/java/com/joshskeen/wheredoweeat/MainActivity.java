package com.joshskeen.wheredoweeat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.joshskeen.wheredoweeat.service.YelpService;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class MainActivity extends BaseActivity {

    @Inject
    YelpService mYelpService;

    @InjectView(R.id.what_should_i_eat_button)
    Button mWhatShouldIEatButton;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        ButterKnife.inject(this);

        mYelpService.search("pizza")
        .subscribeOn(Schedulers.newThread()) //
        .observeOn(AndroidSchedulers.mainThread()) //Subscribers hear about the events on the main thread
        .flatMap(searchResponse -> Observable.from(searchResponse.mBusinesses))
        .filter(business -> business.mRating >= 3.5)
        .subscribe(business -> Log.d(TAG, "got business: " + business));
    }

}
