package com.elanyudho.core.model.repository

import com.elanyudho.core.model.model.DetailGame
import com.elanyudho.core.model.model.Game
import com.elanyudho.core.util.vo.Either
import com.sentuh.core.util.exception.Failure

interface HomeRepository {
    suspend fun getGames(page: String): Either<Failure, List<Game>>

    suspend fun getDetailGame(id: String): Either<Failure, DetailGame>

    suspend fun getSearchGames(page: String, name: String): Either<Failure, List<Game>>

}