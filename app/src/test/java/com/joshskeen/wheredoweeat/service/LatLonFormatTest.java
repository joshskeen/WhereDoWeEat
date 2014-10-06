package com.joshskeen.wheredoweeat.service;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by joshskeen on 10/6/14.
 */
public class LatLonFormatTest {

    private double mLat;
    private double mLon;

    @Before
    public void setup(){
        mLat = 33.687782;
        mLon = -84.353027;
    }

    @Test
    public void testFormatWorksCorrectly(){
        System.out.println(String.format("%.6f,%.6f", mLat, mLon));
    }

}
