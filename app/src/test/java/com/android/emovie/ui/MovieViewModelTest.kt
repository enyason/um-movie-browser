package com.android.emovie.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.android.emovie.domain.usecases.GetLatestMovies
import com.android.emovie.domain.usecases.GetPopularMovies
import com.android.emovie.domain.usecases.GetUpcomingMovies
import com.android.emovie.utils.Movie
import com.android.emovie.utils.TestDispatcher
import com.android.emovie.utils.observeWithMock
import com.android.emovie.utils.toUIModel
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verifyOrder
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MovieViewModelTest {


    private val latestMovies = mockk<GetLatestMovies>(relaxed = true)
    private val popularMovies = mockk<GetPopularMovies>()
    private val upcomingMovies = mockk<GetUpcomingMovies>()

    private val testDispatcher = TestDispatcher()
    private val sut = MovieViewModel(latestMovies, popularMovies, upcomingMovies, testDispatcher)


    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()


    @Test
    fun `test get latest movies is returned`() = runTest {

        val movies = Movie.buildDomainModels()

        coEvery { latestMovies.execute() } returns flowOf(movies)


        val observer = sut.viewState.observeWithMock()

        sut.getLatestMovies()

        val slot = slot<MovieViewModel.ViewState.Content>()

        verifyOrder {
            observer.onChanged(MovieViewModel.ViewState.Loading)
            observer.onChanged(capture(slot))
        }

        val moviesUIModel = movies.map { it.toUIModel() }
        assertThat(moviesUIModel).isEqualTo(slot.captured.movies)
    }

    @Test
    fun `test get popular movies is returned`() = runTest {

        val movies = Movie.buildDomainModels()

        coEvery { popularMovies.execute() } returns flowOf(movies)


        val observer = sut.viewState.observeWithMock()

        sut.getPopularMovies()

        val slot = slot<MovieViewModel.ViewState.Content>()

        verifyOrder {
            observer.onChanged(MovieViewModel.ViewState.Loading)
            observer.onChanged(capture(slot))
        }

        val moviesUIModel = movies.map { it.toUIModel() }
        assertThat(moviesUIModel).isEqualTo(slot.captured.movies)
    }

    @Test
    fun `test get upcoming movies is returned`() = runTest {

        val movies = Movie.buildDomainModels()

        coEvery { upcomingMovies.execute() } returns flowOf(movies)


        val observer = sut.viewState.observeWithMock()

        sut.getUpcomingMovies()

        val slot = slot<MovieViewModel.ViewState.Content>()

        verifyOrder {
            observer.onChanged(MovieViewModel.ViewState.Loading)
            observer.onChanged(capture(slot))
        }

        val moviesUIModel = movies.map { it.toUIModel() }
        assertThat(moviesUIModel).isEqualTo(slot.captured.movies)
    }


    @Test
    fun `test error state`() = runTest {

        val errorMessage = "Could not fetch movies"

        coEvery { latestMovies.execute() } returns flow { throw Throwable(errorMessage) }

        val observer = sut.viewState.observeWithMock()

        sut.getLatestMovies()

        val slot = slot<MovieViewModel.ViewState.Error>()

        verifyOrder {
            observer.onChanged(MovieViewModel.ViewState.Loading)
            observer.onChanged(capture(slot))
        }

        assertThat(slot.captured.msg).isEqualTo(errorMessage)
    }

}