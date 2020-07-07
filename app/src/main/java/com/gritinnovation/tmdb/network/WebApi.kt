package com.gritinnovation.tmdb.network

import com.gritinnovation.tmdb.model.DataModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WebApi {

    @GET("3/discover/movie")
    fun getMovieList(
        @Query("sort_by") sortBy: String,
        @Query("api_key") apiKey: String
    ): Single<DataModel>
}