package com.example.movieapp.data.repository

import com.example.movieapp.data.MovieApi
import com.example.movieapp.data.local.MovieDao
import com.example.movieapp.data.remote.TrendingResponse
import com.example.movieapp.utils.Resource
import com.example.movieapp.utils.apiCall

class MovieRepositoryImpl(private val dao: MovieDao, private val api: MovieApi) : MovieRepository {

    override suspend fun getTrendingMovies(
        media_type: String,
        time_window: String,
        api_key: String
    ): Resource<TrendingResponse> {
        return apiCall {
            api.getTrendingMovies(media_type, time_window, api_key)
        }
    }


}
