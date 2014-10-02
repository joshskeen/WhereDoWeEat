package com.joshskeen.wheredoweeat.inject;

import com.joshskeen.wheredoweeat.MainActivity;
import com.joshskeen.wheredoweeat.WhereDoWeEatApplication;
import com.joshskeen.wheredoweeat.service.YelpService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by joshskeen on 10/2/14.
 */

@Module(injects = {MainActivity.class}, complete = true)
public class WhereDoWeEatApplicationModule {

    private final WhereDoWeEatApplication mApplication;

    public WhereDoWeEatApplicationModule(WhereDoWeEatApplication application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    public YelpService provideYelpService() {
        return YelpService.newInstance();
    }

}
