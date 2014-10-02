package com.joshskeen.wheredoweeat.service;

import com.joshskeen.wheredoweeat.service.response.SearchResponse;

import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by joshskeen on 10/2/14.
 */
public interface YelpServiceInterface {
    @GET("/search")
    public SearchResponse search(
            @Query("location") String location,
            @Query("cll") String latAndLong,
            @Query("term") String term,
            @Query("radius_filter") Integer radiusInMeters);
}
