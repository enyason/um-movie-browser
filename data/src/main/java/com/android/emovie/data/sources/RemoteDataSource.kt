package com.android.emovie.data.sources

import com.android.emovie.data.model.MovieData
import com.android.emovie.domain.usecases.GetMoviesRemoteUseCase
import com.android.emovie.domain.usecases.GetMoviesUseCase
import io.reactivex.Single

interface RemoteDataSource {

    fun getMovies(params: GetMoviesRemoteUseCase.Params): Single<List<MovieData>>
}