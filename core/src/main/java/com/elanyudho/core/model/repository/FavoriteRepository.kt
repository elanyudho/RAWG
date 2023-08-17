package com.elanyudho.core.model.repository

import com.elanyudho.core.model.model.Game

interface FavoriteRepository {

    suspend fun getFavoriteGames(): List<Game>

    suspend fun insertFavorite(game: Game)

    suspend fun deleteFavorite(id: String)

    suspend fun searchByName(name: String): List<Game>
}