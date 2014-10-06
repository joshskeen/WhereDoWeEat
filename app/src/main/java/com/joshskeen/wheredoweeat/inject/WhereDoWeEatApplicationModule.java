package com.joshskeen.wheredoweeat.inject;

import com.joshskeen.wheredoweeat.HomeActivity;
import com.joshskeen.wheredoweeat.MainActivity;
import com.joshskeen.wheredoweeat.WhereDoWeEatApplication;
import com.joshskeen.wheredoweeat.model.LocationProvider;
import com.joshskeen.wheredoweeat.service.YelpService;
import com.joshskeen.wheredoweeat.service.YelpServiceManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by joshskeen on 10/2/14.
 */

@Module(injects = {MainActivity.class, HomeActivity.class}, complete = true)
public class WhereDoWeEatApplicationModule {

    private final WhereDoWeEatApplication mApplication;

    public WhereDoWeEatApplicationModule(WhereDoWeEatApplication application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    public YelpServiceManager provideYelpServiceManager() {
        return new YelpServiceManager(YelpService.newInstance(), provideLocationManager());
    }

    @Provides
    @Singleton
    public LocationProvider provideLocationManager() {
        return new LocationProvider(mApplication.getApplicationContext(), null);
    }

}
