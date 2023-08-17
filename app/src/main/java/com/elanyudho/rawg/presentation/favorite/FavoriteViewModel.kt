package com.elanyudho.rawg.presentation.favorite

import androidx.lifecycle.viewModelScope
import com.elanyudho.core.abstraction.BaseViewModel
import com.elanyudho.core.dispatcher.DispatcherProvider
import com.elanyudho.core.model.model.Game
import com.elanyudho.core.model.usecase.favorite.GetFavoriteGamesUseCase
import com.elanyudho.core.model.usecase.favorite.GetSearchFavoriteGameUseCase
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
    private val getFavoriteGamesUseCase: GetFavoriteGamesUseCase,
    private val getSearchFavoriteGameUseCase: GetSearchFavoriteGameUseCase
) : BaseViewModel<FavoriteViewModel.FavoriteUiState>() {

    sealed class FavoriteUiState {
        object Loading: FavoriteUiState()
        data class GamesLoaded(val data: List<Game>) : FavoriteUiState()
    }

    fun getFavoriteGames() {
        _uiState.value = FavoriteUiState.Loading
        viewModelScope.launch(dispatcherProvider.io) {
            val data = getFavoriteGamesUseCase.go()
            withContext(dispatcherProvider.main) {
                _uiState.value = FavoriteUiState.GamesLoaded(data)
            }
        }
    }

    fun getSearchFavoriteGameByName(name: String) {
        _uiState.value = FavoriteUiState.Loading
        viewModelScope.launch(dispatcherProvider.io) {
            val data = getSearchFavoriteGameUseCase.go(name)
            withContext(dispatcherProvider.main) {
                _uiState.value = FavoriteUiState.GamesLoaded(data)
            }
        }
    }
}