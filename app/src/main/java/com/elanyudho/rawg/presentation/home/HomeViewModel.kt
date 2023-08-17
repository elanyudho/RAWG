package com.elanyudho.rawg.presentation.home

import androidx.lifecycle.viewModelScope
import com.elanyudho.core.abstraction.BaseViewModel
import com.elanyudho.core.model.model.Game
import com.elanyudho.core.model.usecase.home.GetGamesUseCase
import com.elanyudho.core.util.extension.onError
import com.elanyudho.core.util.extension.onSuccess
import com.elanyudho.core.dispatcher.DispatcherProvider
import com.elanyudho.core.model.usecase.home.GetSearchGamesUseCase
import com.sentuh.core.util.exception.Failure
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
    private val getGamesUseCase: GetGamesUseCase,
    private val getSearchGamesUseCase: GetSearchGamesUseCase
) : BaseViewModel<HomeViewModel.HomeUiState>() {

    sealed class HomeUiState {
        object InitialLoading: HomeUiState()
        object PagingLoading: HomeUiState()
        data class GamesLoaded(val data: List<Game>) : HomeUiState()
        data class FailedLoaded(val failure: Failure) : HomeUiState()
    }

    fun getGames(page: Long) {
        _uiState.value = if (page == 1L) {
            HomeUiState.InitialLoading
        }else{
            HomeUiState.PagingLoading
        }

        viewModelScope.launch(dispatcherProvider.io) {
            getGamesUseCase.run(page.toString())
                .onSuccess {
                    withContext(dispatcherProvider.main) {
                        _uiState.value = HomeUiState.GamesLoaded(it)
                    }
                }
                .onError {
                    withContext(dispatcherProvider.main) {
                        _uiState.value = HomeUiState.FailedLoaded(it)
                    }
                }
        }
    }

    fun getSearchGames(page: Long, name: String) {
        _uiState.value = if (page == 1L) {
            HomeUiState.InitialLoading
        }else{
            HomeUiState.PagingLoading
        }

        viewModelScope.launch(dispatcherProvider.io) {
            getSearchGamesUseCase.run(GetSearchGamesUseCase.Params(page.toString(), name))
                .onSuccess {
                    withContext(dispatcherProvider.main) {
                        _uiState.value = HomeUiState.GamesLoaded(it)
                    }
                }
                .onError {
                    withContext(dispatcherProvider.main) {
                        _uiState.value = HomeUiState.FailedLoaded(it)
                    }
                }
        }
    }
}