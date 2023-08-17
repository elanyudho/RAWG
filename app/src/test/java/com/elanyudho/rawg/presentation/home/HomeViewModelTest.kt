package com.elanyudho.rawg.presentation.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.elanyudho.core.dispatcher.DispatcherProvider
import com.elanyudho.core.model.model.Game
import com.elanyudho.core.model.usecase.home.GetGamesUseCase
import com.elanyudho.core.model.usecase.home.GetSearchGamesUseCase
import com.elanyudho.core.util.extension.onSuccess
import com.elanyudho.core.util.vo.Either
import com.elanyudho.rawg.dummydata.DummyData
import com.sentuh.core.util.exception.Failure
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class HomeViewModelTest {

    // The TestCoroutineDispatcher will be used to control the execution of coroutines in the test
    private val testDispatcher = TestCoroutineDispatcher()

    // The class under test
    private lateinit var homeViewModel: HomeViewModel

    // Mocked dependencies
    @Mock
    private lateinit var dispatcherProvider: DispatcherProvider

    @Mock
    private lateinit var getGameUseCase: GetGamesUseCase

    @Mock
    private lateinit var getSearchGamesUseCase: GetSearchGamesUseCase

    private val dummyGame = DummyData.generateDummyGameEntity()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)

        Mockito.`when`(dispatcherProvider.io).thenReturn(testDispatcher)
        Mockito.`when`(dispatcherProvider.main).thenReturn(testDispatcher)

        homeViewModel = HomeViewModel(dispatcherProvider, getGameUseCase, getSearchGamesUseCase)
    }

    @Test
    fun `when Get Games Should Not Null and Return Success`() = runTest() {
        // Given
        val expectedGames: Either<Failure, List<Game>> = Either.Success(dummyGame)

        Mockito.`when`(getGameUseCase.run("1")).thenReturn(expectedGames)

        // Advance the TestCoroutineDispatcher to execute coroutines
        testDispatcher.scheduler.advanceUntilIdle()

        //when
        homeViewModel.getGames(1L)

        assertNotNull(homeViewModel.uiState.value)
        assertTrue(homeViewModel.uiState.value is HomeViewModel.HomeUiState.GamesLoaded)
        val gamesLoadedState = homeViewModel.uiState.value as HomeViewModel.HomeUiState.GamesLoaded
        assertEquals(dummyGame, gamesLoadedState.data)
    }

    @Test
    fun `when Get Games By Search Should Not Null and Return Success`() = runTest() {
        // Given
        val expectedGames: Either<Failure, List<Game>> = Either.Success(dummyGame)

        Mockito.`when`(getSearchGamesUseCase.run(GetSearchGamesUseCase.Params("1", "Grand"))).thenReturn(expectedGames)

        // Advance the TestCoroutineDispatcher to execute coroutines
        testDispatcher.scheduler.advanceUntilIdle()

        //when
        homeViewModel.getSearchGames(1L, name = "Grand")

        assertNotNull(homeViewModel.uiState.value)
        assertTrue(homeViewModel.uiState.value is HomeViewModel.HomeUiState.GamesLoaded)
        val gamesLoadedState = homeViewModel.uiState.value as HomeViewModel.HomeUiState.GamesLoaded
        assertEquals(dummyGame, gamesLoadedState.data)
    }
}
