package com.android.emovie.remote.api

import com.android.emovie.remote.models.MovieApiResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query


interface MovieApi {

    @GET("4/discover/movie")
    fun getPopularMovies(@Query("sort_by") sortBy: String = "popularity.desc"): Single<MovieApiResponse>
}