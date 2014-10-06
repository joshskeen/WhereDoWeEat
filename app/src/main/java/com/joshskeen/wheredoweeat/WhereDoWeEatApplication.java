package com.joshskeen.wheredoweeat;

import android.app.Application;
import android.content.Context;

import com.crittercism.app.Crittercism;
import com.joshskeen.wheredoweeat.inject.WhereDoWeEatApplicationModule;

import dagger.ObjectGraph;

/**
 * Created by joshskeen on 10/2/14.
 */
public class WhereDoWeEatApplication extends Application {

    private ObjectGraph mObjectGraph;

    @Override
    public void onCreate() {
        super.onCreate();
        Crittercism.initialize(getApplicationContext(), "543314db1787844812000003");
        mObjectGraph = ObjectGraph.create(new WhereDoWeEatApplicationModule(this));
    }

    public static WhereDoWeEatApplication get(Context context) {
        return (WhereDoWeEatApplication) context.getApplicationContext();
    }

    public final void inject(Object object) {
        mObjectGraph.inject(object);
    }

}
