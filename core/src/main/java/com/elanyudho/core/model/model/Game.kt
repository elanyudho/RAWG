package com.elanyudho.core.model.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "favorite", indices = [Index(value = [ "id" ], unique = true)] )
data class Game(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String = "",

    @ColumnInfo(name = "name")
    val name: String = "",

    @ColumnInfo(name = "releaseDate")
    val releaseDate: String = "",

    @ColumnInfo(name = "rating")
    val rating: String = "",

    @ColumnInfo(name = "image")
    val image: String = "",

)
