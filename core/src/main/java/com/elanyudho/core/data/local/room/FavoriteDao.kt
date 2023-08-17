package com.elanyudho.core.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.elanyudho.core.model.model.Game

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(game: Game)

    @Query("DELETE FROM favorite WHERE id = :id")
    fun delete(id: String)

    @Query("SELECT * from favorite ORDER BY id ASC")
    fun getAllFavorite(): List<Game>

    @Query("SELECT * FROM favorite WHERE name LIKE :name")
    fun searchByName(name: String): List<Game>
}