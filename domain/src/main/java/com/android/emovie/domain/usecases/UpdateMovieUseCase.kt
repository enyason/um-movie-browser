package com.android.emovie.domain.usecases

import com.android.emovie.domain.repositories.MovieRepository
import com.android.emovie.domain.usecases.base.CompleteableUseCase
import io.reactivex.Completable
import javax.inject.Inject

class UpdateMovieUseCase @Inject constructor(private val movieRepository: MovieRepository) :
    CompleteableUseCase<Unit, UpdateMovieUseCase.Params>() {


    data class Params(
        val movieId: Int,
        val isFavourite: Boolean
    )

    override fun buildFlowUseCase(params: Params?): Completable {
        return movieRepository.updateMovie(params!!)
    }

}