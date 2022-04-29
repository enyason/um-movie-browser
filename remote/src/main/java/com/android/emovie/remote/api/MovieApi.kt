package com.android.emovie.remote.api

import com.android.emovie.remote.models.MovieApiResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface MovieApi {

    @GET("3/movie/popular")
    suspend fun getPopularMovies(): MovieApiResponse

    @GET("3/discover/movie")
    suspend fun getLatestMovies(@Query("release_date.gte") releaseDate: String = "2022"): MovieApiResponse

    @GET("3/movie/upcoming")
    suspend fun getUpcomingMovies(): MovieApiResponse
}