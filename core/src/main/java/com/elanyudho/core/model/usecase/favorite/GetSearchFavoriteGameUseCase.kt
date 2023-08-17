package com.elanyudho.core.model.usecase.favorite

import com.elanyudho.core.model.model.Game
import com.elanyudho.core.model.repository.FavoriteRepository
import javax.inject.Inject

class GetSearchFavoriteGameUseCase @Inject constructor(private val repo: FavoriteRepository) {
    suspend fun go(name: String): List<Game> {
        return repo.searchByName(name)
    }
}