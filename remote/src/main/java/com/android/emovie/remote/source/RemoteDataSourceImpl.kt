package com.android.emovie.remote.source

import com.android.emovie.data.model.MovieData
import com.android.emovie.data.sources.RemoteDataSource
import com.android.emovie.domain.usecases.GetMoviesRemoteUseCase
import com.android.emovie.domain.usecases.GetMoviesUseCase
import com.android.emovie.remote.api.MovieApi
import io.reactivex.Single
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(private val api: MovieApi) : RemoteDataSource {
    override fun getMovies(params: GetMoviesRemoteUseCase.Params): Single<List<MovieData>> {

        return api.getPopularMovies(params.sortBy).map { response ->

            response.results.map { movieRemote ->

                with(movieRemote) {
                    MovieData(
                        adult,
                        backdrop_path,
                        genre_ids,
                        id,
                        original_language,
                        original_title,
                        overview,
                        popularity,
                        poster_path,
                        release_date,
                        title,
                        video,
                        vote_average,
                        vote_count,
                        false
                    )
                }
            }
        }
    }


}