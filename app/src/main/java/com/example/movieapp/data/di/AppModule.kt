package com.example.movieapp.data.di

import android.app.Application
import androidx.room.Room
import com.example.movieapp.data.MovieApi
import com.example.movieapp.data.MovieApi.Companion.BASE_URL
import com.example.movieapp.data.local.MovieDao
import com.example.movieapp.data.local.MovieDatabase
import com.example.movieapp.data.repository.MovieRepository
import com.example.movieapp.data.repository.MovieRepositoryImpl
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    @Provides
    @Singleton
    fun provideMovieApi(client: OkHttpClient): MovieApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .client(client)
            .build()
            .create(MovieApi::class.java)
    }

    @Provides
    @Singleton
    fun provideMovieDatabase(app: Application): MovieDatabase {
        return Room.databaseBuilder(
            app,
            MovieDatabase::class.java,
            "movie_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideMovieRepository(dao: MovieDao, api: MovieApi): MovieRepository {
        return MovieRepositoryImpl(dao, api)
    }

    @Singleton
    @Provides
    fun providesFootballFixturesDao(database: MovieDatabase): MovieDao {
        return database.movieDao
    }
}
