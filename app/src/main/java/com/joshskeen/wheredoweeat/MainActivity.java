package com.joshskeen.wheredoweeat;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class MainActivity extends Activity {

    @InjectView(R.id.what_should_i_eat_button)
    Button mWhatShouldIEatButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        ButterKnife.inject(this);
        mWhatShouldIEatButton.setOnClickListener((View v) -> {
//            System.out.println("FOO!");
        });
    }

}
