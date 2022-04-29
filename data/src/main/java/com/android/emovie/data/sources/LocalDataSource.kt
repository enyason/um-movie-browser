package com.android.emovie.data.sources

import com.android.emovie.data.model.MovieData
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    suspend fun getLatestMovies(): List<MovieData>
    suspend fun getPopularMovies(): List<MovieData>
    suspend fun getUpcomingMovies(): List<MovieData>

    suspend fun saveLatestMovies(movies : List<MovieData>)
    suspend fun savePopularMovies(movies : List<MovieData>)
    suspend fun saveUpcomingMovies(movies : List<MovieData>)
}