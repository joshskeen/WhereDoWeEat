package com.joshskeen.wheredoweeat.service;

import com.joshskeen.wheredoweeat.service.response.SearchResponse;

import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by joshskeen on 10/2/14.
 */
public interface YelpServiceInterface {
    @GET("/performSearch")
    public Observable<SearchResponse> search(
            @Query("ll") String latAndLong,
            @Query("term") String term,
            @Query("radius_filter") Integer radiusInMeters);
}
