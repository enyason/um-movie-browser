package com.android.emovie.local

import androidx.room.*
import com.android.emovie.local.model.LatestMovieLocal
import com.android.emovie.local.model.PopularMovieLocal
import com.android.emovie.local.model.UpcomingMovieLocal

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveLatestMovies(movie: List<LatestMovieLocal>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun savePopularMovies(movie: List<PopularMovieLocal>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveUpcomingMovies(movie: List<UpcomingMovieLocal>)


    @Query("SELECT * FROM latest_movie_table")
    suspend fun getLatestMovies(): List<LatestMovieLocal>

    @Query("SELECT * FROM popular_movie_table")
    suspend fun getPopularMovies(): List<PopularMovieLocal>

    @Query("SELECT * FROM upcoming_movie_table")
    suspend fun getUpcomingMovies(): List<UpcomingMovieLocal>
}