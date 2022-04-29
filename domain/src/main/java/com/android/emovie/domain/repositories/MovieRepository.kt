package com.android.emovie.domain.repositories

import com.android.emovie.domain.models.MovieDomain
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getLatestMovies(): Flow<List<MovieDomain>>
    fun getPopularMovies(): Flow<List<MovieDomain>>
    fun getUpcomingMovies(): Flow<List<MovieDomain>>
}