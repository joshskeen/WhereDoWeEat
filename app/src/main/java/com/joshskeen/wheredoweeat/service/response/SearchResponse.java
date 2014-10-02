package com.joshskeen.wheredoweeat.service.response;


import com.google.gson.annotations.SerializedName;
import com.joshskeen.wheredoweeat.model.Business;

import java.util.List;

public class SearchResponse {

    @SerializedName("businesses")
    public List<Business> mBusinesses;

}
