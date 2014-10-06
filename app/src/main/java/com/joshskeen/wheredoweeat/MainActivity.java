package com.joshskeen.wheredoweeat;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.joshskeen.wheredoweeat.service.YelpServiceManager;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import rx.subscriptions.CompositeSubscription;


public class MainActivity extends BaseActivity {

    CompositeSubscription mCompositeSubscription = new CompositeSubscription();

    @Inject
    YelpServiceManager mYelpServiceManager;

    @InjectView(R.id.business_name)
    TextView mBusinessName;

    @InjectView(R.id.try_again_button)
    Button mTryAgainButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        ButterKnife.inject(this);
        loadWhereToEatRecommendation();
        mTryAgainButton.setOnClickListener(v -> loadWhereToEatRecommendation());
    }

    private void loadWhereToEatRecommendation() {
        mCompositeSubscription.clear();
        mCompositeSubscription.add(mYelpServiceManager.performSearch().subscribe(business -> mBusinessName.setText(business.mName)));
    }

    @Override
    protected void onStop() {
        super.onStop();
        mCompositeSubscription.unsubscribe();
    }
}
