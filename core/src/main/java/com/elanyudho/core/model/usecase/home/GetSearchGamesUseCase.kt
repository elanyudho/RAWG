package com.elanyudho.core.model.usecase.home

import com.elanyudho.core.abstraction.UseCase
import com.elanyudho.core.model.model.Game
import com.elanyudho.core.model.repository.HomeRepository
import com.elanyudho.core.util.vo.Either
import com.sentuh.core.util.exception.Failure
import javax.inject.Inject

class GetSearchGamesUseCase @Inject constructor(private val repo: HomeRepository) : UseCase<List<Game>, GetSearchGamesUseCase.Params>(){

    data class Params(
        val page: String,
        val name: String
    )
    override suspend fun run(params: Params): Either<Failure, List<Game>> {
        return repo.getSearchGames(params.page, params.name)
    }
}