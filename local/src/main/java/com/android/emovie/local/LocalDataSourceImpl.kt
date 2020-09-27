package com.android.emovie.local

import com.android.emovie.data.model.MovieData
import com.android.emovie.data.sources.LocalDataSource
import com.android.emovie.domain.usecases.UpdateMovieUseCase
import com.android.emovie.local.model.MovieLocal
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(private val db: AppDataBase) : LocalDataSource {
    override fun updateMovie(params: UpdateMovieUseCase.Params): Completable {

        return db.movieDao().updateMovie(params.movieId, params.isFavourite)
    }

    override fun getMovies(): Observable<List<MovieData>> {

        return db.movieDao().getMovies().map {
            it.map { movie ->
                with(movie) {
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
                        isFavourite
                    )
                }
            }
        }
    }

    override fun saveMovies(list: List<MovieData>): Completable {
        val movies = list.map { movie ->

            with(movie) {
                MovieLocal(
                    id,
                    adult,
                    backdrop_path,
                    genre_ids,
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
                    isFavourite
                )
            }
        }
        return db.movieDao().saveMovies(movies)
    }


}