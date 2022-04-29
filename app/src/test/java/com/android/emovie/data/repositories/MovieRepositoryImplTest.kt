package com.android.emovie.data.repositories

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.android.emovie.data.sources.LocalDataSource
import com.android.emovie.data.sources.RemoteDataSource
import com.android.emovie.utils.Movie
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MovieRepositoryImplTest {

    private val remoteDataSource = mockk<RemoteDataSource>()
    private val localDataSource = mockk<LocalDataSource>(relaxed = true)
    private val sut = MovieRepositoryImpl(remoteDataSource, localDataSource)

    @Test
    fun `test get latest movies is returned`() = runTest {

        val movies = Movie.buildDataModels()

        coEvery { localDataSource.getLatestMovies() } returns movies
        coEvery { remoteDataSource.getLatestMovies() } returns movies

        val flowResult = sut.getLatestMovies().toList()

        assertThat(flowResult.size).isEqualTo(2)
        assertThat(flowResult.first().first().id).isEqualTo(movies.first().id)
        coVerify { localDataSource.saveLatestMovies(any()) }
    }

    @Test
    fun `test get popular movies is returned`() = runTest {

        val movies = Movie.buildDataModels()

        coEvery { localDataSource.getPopularMovies() } returns movies
        coEvery { remoteDataSource.getPopularMovies() } returns movies

        val flowResult = sut.getPopularMovies().toList()


        assertThat(flowResult.size).isEqualTo(2)
        assertThat(flowResult.first().first().id).isEqualTo(movies.first().id)
    }


    @Test
    fun `test get upcoming movies is returned`() = runTest {

        val movies = Movie.buildDataModels()

        coEvery { localDataSource.getUpcomingMovies() } returns movies
        coEvery { remoteDataSource.getUpcomingMovies() } returns movies

        val flowResult = sut.getUpcomingMovies().toList()


        assertThat(flowResult.size).isEqualTo(2)
        assertThat(flowResult.first().first().id).isEqualTo(movies.first().id)
    }

    @Test
    fun `when local source is empty latest movies is emitted once`() = runTest {

        val movies = Movie.buildDataModels()

        coEvery { localDataSource.getLatestMovies() } returns emptyList()
        coEvery { remoteDataSource.getLatestMovies() } returns movies

        val flowResult = sut.getLatestMovies().toList()

        assertThat(flowResult.size).isEqualTo(1)
        assertThat(flowResult.first()).isNotEmpty()
    }

    @Test
    fun `when local source is empty popular movies is emitted once`() = runTest {

        val movies = Movie.buildDataModels()

        coEvery { localDataSource.getPopularMovies() } returns emptyList()
        coEvery { remoteDataSource.getPopularMovies() } returns movies

        val flowResult = sut.getPopularMovies().toList()

        assertThat(flowResult.size).isEqualTo(1)
        assertThat(flowResult.first()).isNotEmpty()
    }

    @Test
    fun `when local source is empty upcoming movies is emitted once`() = runTest {

        val movies = Movie.buildDataModels()

        coEvery { localDataSource.getUpcomingMovies() } returns emptyList()
        coEvery { remoteDataSource.getUpcomingMovies() } returns movies

        val flowResult = sut.getUpcomingMovies().toList()

        assertThat(flowResult.size).isEqualTo(1)
        assertThat(flowResult.first()).isNotEmpty()
    }


    @Test
    fun `Given local source is empty When getting movies, save to db`() = runTest {

        val movies = Movie.buildDataModels()

        coEvery { localDataSource.getLatestMovies() } returns emptyList()
        coEvery { remoteDataSource.getLatestMovies() } returns movies

        val flowResult = sut.getLatestMovies().toList()

        assertThat(flowResult.size).isEqualTo(1)
        assertThat(flowResult.first().first().id).isEqualTo(movies.first().id)
        coVerify { localDataSource.saveLatestMovies(any()) }
    }
}