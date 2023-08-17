package com.elanyudho.core.data.repository

import com.elanyudho.core.data.local.LocalDataSource
import com.elanyudho.core.model.model.Game
import com.elanyudho.core.model.repository.FavoriteRepository
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource
) : FavoriteRepository {
    override suspend fun getFavoriteGames(): List<Game> {
        return localDataSource.getFavoriteGames()
    }

    override suspend fun insertFavorite(game: Game) {
        localDataSource.addFavoriteGame(game)
    }

    override suspend fun deleteFavorite(id: String) {
        localDataSource.deleteFavoriteGame(id)
    }

    override suspend fun searchByName(name: String): List<Game> {
        return localDataSource.searchByName(name)
    }

}