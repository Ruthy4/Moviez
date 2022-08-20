package com.example.movieapp.data.local.converters

import androidx.room.TypeConverter
import com.example.movieapp.data.local.entity.TrendingEntity
import com.example.movieapp.data.remote.Trending
import com.google.gson.Gson

class TrendingConverter {
    companion object {
        @TypeConverter
        @JvmStatic
        fun fromStringToTrending(value: String?): TrendingEntity? {
            return if (value == null) null else Gson().fromJson(value, TrendingEntity::class.java)
        }

        @TypeConverter
        @JvmStatic
        fun fromTrendingToString(trending: TrendingEntity?): String? {
            return Gson().toJson(trending)
        }

        @TypeConverter
        @JvmStatic
        fun fromString(string: String?): List<String>? {
            return string?.split(",")?.map { it }
        }

        @TypeConverter
        @JvmStatic
        fun toString(stringList: List<String>?): String? {
            return stringList?.joinToString(",")
        }

        @TypeConverter
        @JvmStatic
        fun fromStringToList(string: String?): List<Int>? {
            return string?.substring(1, string.length - 1)?.split(",")?.map { it.toInt() }
        }

        @TypeConverter
        @JvmStatic
        fun fromListToString(genre_ids: List<Int>?): String {
            return genre_ids.toString()
        }
    }
}