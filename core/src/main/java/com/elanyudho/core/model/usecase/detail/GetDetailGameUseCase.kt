package com.elanyudho.core.model.usecase.detail

import com.elanyudho.core.abstraction.UseCase
import com.elanyudho.core.model.model.DetailGame
import com.elanyudho.core.model.repository.HomeRepository
import com.elanyudho.core.util.vo.Either
import com.sentuh.core.util.exception.Failure
import javax.inject.Inject

class GetDetailGameUseCase @Inject constructor(private val repo: HomeRepository) : UseCase<DetailGame, String>(){

    override suspend fun run(params: String): Either<Failure, DetailGame> {
        return repo.getDetailGame(params)
    }
}