package com.joshskeen.wheredoweeat;

import android.os.Bundle;
import android.widget.Button;

import com.joshskeen.wheredoweeat.service.YelpServiceManager;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import rx.subscriptions.CompositeSubscription;


public class MainActivity extends BaseActivity {

    CompositeSubscription mCompositeSubscription = new CompositeSubscription();

    @Inject
    YelpServiceManager mYelpServiceManager;

    @InjectView(R.id.what_should_i_eat_button)
    Button mWhatShouldIEatButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        ButterKnife.inject(this);
        mCompositeSubscription.add(mYelpServiceManager.performSearch().subscribe());
    }

    @Override
    protected void onStop() {
        super.onStop();
        mCompositeSubscription.unsubscribe();
    }
}
