package com.elanyudho.core.data.local

import com.elanyudho.core.data.local.room.FavoriteDao
import com.elanyudho.core.model.model.Game
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val favoriteDao: FavoriteDao) {
    fun getFavoriteGames(): List<Game> = favoriteDao.getAllFavorite()

    fun addFavoriteGame(game: Game) = favoriteDao.insert(game)

    fun deleteFavoriteGame(id: String) = favoriteDao.delete(id)

    fun searchByName(name: String) = favoriteDao.searchByName("%$name%")

}