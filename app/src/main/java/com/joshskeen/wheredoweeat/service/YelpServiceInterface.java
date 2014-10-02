package com.joshskeen.wheredoweeat.service;

import com.joshskeen.wheredoweeat.service.response.SearchResponse;

import java.util.List;

import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by joshskeen on 10/2/14.
 */
public interface YelpServiceInterface {
    @GET("search")
    public Observable<List<SearchResponse>> search(@Query("cll") String latAndLong,
                                                   @Query("term") String term,
                                                   @Query("radius_filter") Integer radiusInMeters);
}
