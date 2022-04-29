package com.android.emovie.domain.usecases

import com.android.emovie.domain.models.MovieDomain
import com.android.emovie.domain.repositories.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetLatestMovies @Inject constructor(
    private val repository: MovieRepository
) {

    fun execute(): Flow<List<MovieDomain>> {
        return repository.getLatestMovies()
    }
}
