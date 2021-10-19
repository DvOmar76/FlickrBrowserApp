package com.example.flickrbrowserapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface APIInterface {
    @PUT("/services/rest/")
    fun search(
        @Query("tags")tags: String ,
        @Query("api_key")api_key: String? ="960f0be46c9c43c520709ee47cbfb5bc",
        @Query("method")method: String? ="flickr.photos.search",
        @Query("per_page")per_page: String? ="100",

        @Query("format")format: String? ="json",
        @Query("nojsoncallback")nojsoncallback: String? ="1",
        ) :Call<Flickr>


}