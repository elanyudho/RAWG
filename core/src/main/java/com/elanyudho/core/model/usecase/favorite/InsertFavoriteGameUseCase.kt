package com.elanyudho.core.model.usecase.favorite

import com.elanyudho.core.model.model.Game
import com.elanyudho.core.model.repository.FavoriteRepository
import javax.inject.Inject

class InsertFavoriteGameUseCase @Inject constructor(private val repo: FavoriteRepository) {
    suspend fun go(game: Game) {
        return repo.insertFavorite(game)
    }
}