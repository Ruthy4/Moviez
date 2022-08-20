package com.example.movieapp.data

import com.example.movieapp.data.remote.TrendingResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/"
    }

    @GET("/3/trending/{media_type}/{time_window}")
    suspend fun getTrendingMovies(@Path("media_type") media_type: String,
    @Path("time_window")time_window: String,
    @Query("api_key")api_key: String): TrendingResponse
}
