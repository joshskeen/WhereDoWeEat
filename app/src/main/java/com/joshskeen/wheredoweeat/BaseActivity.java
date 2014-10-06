package com.joshskeen.wheredoweeat;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by joshskeen on 10/2/14.
 */
public abstract class BaseActivity extends Activity {

    public String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WhereDoWeEatApplication.get(this).inject(this);
    }

}
