package com.elanyudho.rawg.presentation.favorite

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.elanyudho.core.dispatcher.DispatcherProvider
import com.elanyudho.core.model.model.Game
import com.elanyudho.core.model.usecase.favorite.DeleteFavoriteGameUseCase
import com.elanyudho.core.model.usecase.favorite.GetFavoriteGamesUseCase
import com.elanyudho.core.model.usecase.favorite.GetSearchFavoriteGameUseCase
import com.elanyudho.core.model.usecase.home.GetGamesUseCase
import com.elanyudho.core.model.usecase.home.GetSearchGamesUseCase
import com.elanyudho.core.util.vo.Either
import com.elanyudho.rawg.dummydata.DummyData
import com.elanyudho.rawg.presentation.home.HomeViewModel
import com.sentuh.core.util.exception.Failure
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class FavoriteViewModelTest {

    // The TestCoroutineDispatcher will be used to control the execution of coroutines in the test
    private val testDispatcher = TestCoroutineDispatcher()

    // The class under test
    private lateinit var favoriteViewModel: FavoriteViewModel

    // Mocked dependencies
    @Mock
    private lateinit var dispatcherProvider: DispatcherProvider

    @Mock
    private lateinit var getFavoriteGameUseCase: GetFavoriteGamesUseCase

    @Mock
    private lateinit var getSearchFavoriteGameUseCase: GetSearchFavoriteGameUseCase

    private val dummyGame = DummyData.generateDummyGameEntity()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)

        Mockito.`when`(dispatcherProvider.io).thenReturn(testDispatcher)
        Mockito.`when`(dispatcherProvider.main).thenReturn(testDispatcher)

        favoriteViewModel = FavoriteViewModel(dispatcherProvider, getFavoriteGameUseCase, getSearchFavoriteGameUseCase)
    }

    @Test
    fun `when Get Favorite Games Should Not Null and Return Success`() = runTest() {

        Mockito.`when`(getFavoriteGameUseCase.go()).thenReturn(dummyGame)

        // Advance the TestCoroutineDispatcher to execute coroutines
        testDispatcher.scheduler.advanceUntilIdle()

        //when
        favoriteViewModel.getFavoriteGames()

        assertNotNull(favoriteViewModel.uiState.value)
        assertTrue(favoriteViewModel.uiState.value is FavoriteViewModel.FavoriteUiState.GamesLoaded)
        val gamesLoadedState = favoriteViewModel.uiState.value as FavoriteViewModel.FavoriteUiState.GamesLoaded
        assertEquals(dummyGame, gamesLoadedState.data)
    }

    @Test
    fun `when Get Search Favorite Games Should Not Null and Return Success`() = runTest() {
        Mockito.`when`(getSearchFavoriteGameUseCase.go("Grand")).thenReturn(dummyGame)

        // Advance the TestCoroutineDispatcher to execute coroutines
        testDispatcher.scheduler.advanceUntilIdle()

        //when
        favoriteViewModel.getSearchFavoriteGameByName("Grand")

        assertNotNull(favoriteViewModel.uiState.value)
        assertTrue(favoriteViewModel.uiState.value is FavoriteViewModel.FavoriteUiState.GamesLoaded)
        val gamesLoadedState = favoriteViewModel.uiState.value as FavoriteViewModel.FavoriteUiState .GamesLoaded
        assertEquals(dummyGame, gamesLoadedState.data)
    }
}
