package com.elanyudho.core.model.usecase.home

import com.elanyudho.core.abstraction.UseCase
import com.elanyudho.core.model.model.Game
import com.elanyudho.core.model.repository.HomeRepository
import com.elanyudho.core.util.vo.Either
import com.sentuh.core.util.exception.Failure
import javax.inject.Inject

class GetGamesUseCase @Inject constructor(private val repo: HomeRepository) : UseCase<List<Game>, String>(){

    override suspend fun run(params: String): Either<Failure, List<Game>> {
        return repo.getGames(params)
    }
}