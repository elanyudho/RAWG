package com.elanyudho.rawg.presentation.detailgame

import androidx.lifecycle.viewModelScope
import com.elanyudho.core.abstraction.BaseViewModel
import com.elanyudho.core.dispatcher.DispatcherProvider
import com.elanyudho.core.model.model.DetailGame
import com.elanyudho.core.model.model.Game
import com.elanyudho.core.model.usecase.detail.GetDetailGameUseCase
import com.elanyudho.core.model.usecase.favorite.DeleteFavoriteGameUseCase
import com.elanyudho.core.model.usecase.favorite.GetFavoriteGamesUseCase
import com.elanyudho.core.model.usecase.favorite.InsertFavoriteGameUseCase
import com.elanyudho.core.util.extension.onError
import com.elanyudho.core.util.extension.onSuccess
import com.sentuh.core.util.exception.Failure
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DetailGameViewModel @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
    private val getDetailGameUseCase: GetDetailGameUseCase,
    private val getFavoriteGamesUseCase: GetFavoriteGamesUseCase,
    private val insertFavoriteGameUseCase: InsertFavoriteGameUseCase,
    private val deleteFavoriteGameUseCase: DeleteFavoriteGameUseCase
) : BaseViewModel<DetailGameViewModel.DetailGameUiState>() {

    sealed class DetailGameUiState {
        object Loading: DetailGameUiState()
        data class DetailGameLoaded(val data: DetailGame) : DetailGameUiState()
        data class FailedLoaded(val failure: Failure) : DetailGameUiState()
        data class IsFavorite(val isFavorite: Boolean) : DetailGameUiState()
    }

    fun getDetailGame(id: String) {
        _uiState.value = DetailGameUiState.Loading
        viewModelScope.launch(dispatcherProvider.io) {
            getDetailGameUseCase.run(id)
                .onSuccess {
                    withContext(dispatcherProvider.main) {
                        _uiState.value = DetailGameUiState.DetailGameLoaded(it)
                    }
                }
                .onError {
                    withContext(dispatcherProvider.main) {
                        _uiState.value = DetailGameUiState.FailedLoaded(it)
                    }
                }
        }
    }

    fun isFavoriteGame(id: String) {
        var isFavorite = false
        viewModelScope.launch (dispatcherProvider.io) {
            for (i in getFavoriteGamesUseCase.go()) {
                if (i.id == id)  {
                    isFavorite = true

                    break
                }
            }
            withContext(dispatcherProvider.main) {
                _uiState.value = DetailGameUiState.IsFavorite(isFavorite)
            }
        }
    }

    fun insertFavorite(game: Game) {
        viewModelScope.launch (dispatcherProvider.io) {
            insertFavoriteGameUseCase.go(game)
        }
    }

    fun deleteFavorite(id: String) {
        viewModelScope.launch (dispatcherProvider.io) {
            deleteFavoriteGameUseCase.go(id)
        }
    }
}