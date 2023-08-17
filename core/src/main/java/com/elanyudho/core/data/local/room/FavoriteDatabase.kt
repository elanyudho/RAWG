package com.elanyudho.core.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.elanyudho.core.model.model.Game

@Database(entities = [Game::class], version = 1, exportSchema = false)
abstract class FavoriteDatabase : RoomDatabase() {

    abstract fun favoriteDao(): FavoriteDao
}