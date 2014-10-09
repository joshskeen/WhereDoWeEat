package com.joshskeen.wheredoweeat;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.joshskeen.wheredoweeat.model.Business;
import com.joshskeen.wheredoweeat.model.LocationProvider;
import com.joshskeen.wheredoweeat.model.StringUtil;

/**
 * Created by joshskeen on 10/9/14.
 */
public class NavigationIntentBuilder {

    public static Intent build(Context context, LocationProvider locationProvider, Business business) {
        String startAddress = StringUtil.formatLatLng(locationProvider.getLocation());
        String endAddress = StringUtil.formatLatLng(business.mLocation);
        String mapsUri = String.format(context.getString(R.string.google_map_uri), startAddress, endAddress);
        return new Intent(Intent.ACTION_VIEW, Uri.parse(mapsUri));
    }
}
