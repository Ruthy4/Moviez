package com.example.movieapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.movieapp.data.local.converters.TrendingConverter
import com.example.movieapp.data.local.entity.TrendingEntity

@Database(entities = [TrendingEntity::class], version = 1)
@TypeConverters(TrendingConverter::class)
abstract class MovieDatabase: RoomDatabase() {
    abstract val movieDao: MovieDao
}
