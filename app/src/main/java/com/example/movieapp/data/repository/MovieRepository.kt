package com.example.movieapp.data.repository

import com.example.movieapp.data.remote.TrendingResponse
import com.example.movieapp.utils.Resource

interface MovieRepository {

    suspend fun getTrendingMovies(
        media_type: String,
        time_window: String,
        api_key: String
    ): Resource<TrendingResponse>

}
