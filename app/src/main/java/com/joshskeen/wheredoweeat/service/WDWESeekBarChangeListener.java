package com.joshskeen.wheredoweeat.service;

import android.widget.SeekBar;

import com.joshskeen.wheredoweeat.MainActivity;

/**
 * Created by joshskeen on 10/9/14.
 */
public abstract class WDWESeekBarChangeListener implements SeekBar.OnSeekBarChangeListener {

    private MainActivity mActivity;

    public WDWESeekBarChangeListener(MainActivity activity) {
        mActivity = activity;
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        mActivity.loadWhereToEatRecommendation();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

}
