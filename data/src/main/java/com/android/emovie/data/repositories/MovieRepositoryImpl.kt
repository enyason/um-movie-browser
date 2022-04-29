package com.android.emovie.data.repositories

import com.android.emovie.data.model.MovieData
import com.android.emovie.data.sources.LocalDataSource
import com.android.emovie.data.sources.RemoteDataSource
import com.android.emovie.domain.models.MovieDomain
import com.android.emovie.domain.repositories.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : MovieRepository {

    override fun getLatestMovies(): Flow<List<MovieDomain>> {
        return flow {
            val localMovies = localDataSource.getLatestMovies().map { it.toDomain() }
            if (localMovies.isNotEmpty()) emit(localMovies)

            val remoteMovies = remoteDataSource.getLatestMovies()
            localDataSource.saveLatestMovies(remoteMovies)
            emit(remoteMovies.map { it.toDomain() })
        }
    }

    override fun getPopularMovies(): Flow<List<MovieDomain>> {
        return flow {
            val localMovies = localDataSource.getPopularMovies().map { it.toDomain() }
            if (localMovies.isNotEmpty()) emit(localMovies)

            val remoteMovies = remoteDataSource.getPopularMovies()
            localDataSource.savePopularMovies(remoteMovies)
            emit(remoteMovies.map { it.toDomain() })
        }
    }

    override fun getUpcomingMovies(): Flow<List<MovieDomain>> {
        return flow {
            val localMovies = localDataSource.getUpcomingMovies().map { it.toDomain() }
            if (localMovies.isNotEmpty()) emit(localMovies)

            val remoteMovies = remoteDataSource.getUpcomingMovies()
            localDataSource.saveUpcomingMovies(remoteMovies)
            emit(remoteMovies.map { it.toDomain() })
        }
    }

    private fun MovieData.toDomain(): MovieDomain {
        return MovieDomain(
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
            vote_count
        )
    }
}

