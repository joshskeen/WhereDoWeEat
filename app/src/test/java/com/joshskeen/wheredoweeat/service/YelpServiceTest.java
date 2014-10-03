package com.joshskeen.wheredoweeat.service;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by joshskeen on 10/2/14.
 */
public class YelpServiceTest {

    private YelpService mService;

    @Before
    public void setup() {
        mService = YelpService.newInstance();
    }

    @Test
    public void testThatYelpServieSupportsMakingSearch() {
//        SearchResponse pizza = mService.performSearch("pizza");
//        System.out.println(pizza);
    }

}
