package com.joshskeen.wheredoweeat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.fest.assertions.api.ANDROID.assertThat;

@RunWith(RobolectricTestRunner.class)
@Config(manifest="./src/main/AndroidManifest.xml", emulateSdk = 18)
public class MainActivityTest {

    private MainActivity mMainActivity;

    @Before
    public void setup() {
        mMainActivity = Robolectric.buildActivity(MainActivity.class).create().get();
    }

    @Test
    public void mainActivityLoadsAsExpected() {
        assertThat(mMainActivity.mWhatShouldIEatButton).hasText("Where should we eat?");
    }

}