package com.android.emovie.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.android.shopmax.local.typeConverters.GenreIdsConverter

@Entity(tableName = "latest_movie_table")
data class LatestMovieLocal(
    @PrimaryKey var id: Int,
     val adult: Boolean,
     val backdrop_path: String,
    @field:TypeConverters(GenreIdsConverter::class)
     val genre_ids: List<Int>,
     val original_language: String,
     val original_title: String,
     val overview: String,
     val popularity: Double,
     val poster_path: String,
     val release_date: String,
     val title: String,
     val video: Boolean,
     val vote_average: Double,
     val vote_count: Int
)