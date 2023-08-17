package com.elanyudho.core.model.usecase.favorite

import com.elanyudho.core.model.repository.FavoriteRepository
import javax.inject.Inject

class DeleteFavoriteGameUseCase @Inject constructor(private val repo: FavoriteRepository) {
    suspend fun go(id: String) {
        return repo.deleteFavorite(id)
    }
}