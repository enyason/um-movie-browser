package com.android.emovie.data.sources

import com.android.emovie.data.model.MovieData

interface RemoteDataSource {
    suspend fun getLatestMovies(): List<MovieData>
    suspend fun getPopularMovies(): List<MovieData>
    suspend fun getUpcomingMovies(): List<MovieData>
}